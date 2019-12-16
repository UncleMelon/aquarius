package myakkastream
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.scaladsl.{Committer, Consumer}
import akka.kafka.{CommitterSettings, ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.stream.{ActorMaterializer, ActorMaterializerSettings, Supervision}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
* 
* @author matthew_wu
* @since 2019-07-26 22:24
*/
object AkkaExceptionTest extends App {
  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()
  implicit val ec = system.dispatcher
  Source(List(1, 2, 3)).map { i =>
    if (i == 2) {
      throw new RuntimeException("Please, don't swallow me!")
    } else {
      i
    }
  }.runForeach { i =>
    println(s"Received $i")
  }.onComplete {
    case Success(_) =>
      println("Done")
    case Failure(e) =>
      println(s"Failed with $e")
  }
}

object AkkaExceptionTest1 extends App {
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  val decider: Supervision.Decider = { e =>
    logger.error("Unhandled exception in stream", e)
    Supervision.Stop
  }

  implicit val system = ActorSystem()
  implicit val materializerSettings = ActorMaterializerSettings(system).withSupervisionStrategy(decider)
  implicit val materializer = ActorMaterializer(materializerSettings)(system)
  implicit val ec = system.dispatcher
  val control = Source(List(1, 2, 3)).map { i =>
    if (i == 2) {
      throw new RuntimeException("Please, don't swallow me!")
    } else {
      i
    }
  }.to(Sink.foreach { i =>
    println(s"Received $i")
  }).run()

}

object AkkaExceptionTest2 extends App {
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  val decider: Supervision.Decider = { e =>
    logger.error("Unhandled exception in stream", e)
    Supervision.Stop
  }

  implicit val system = ActorSystem()
  implicit val materializerSettings = ActorMaterializerSettings(system).withSupervisionStrategy(decider)
  implicit val materializer = ActorMaterializer(materializerSettings)(system)
  implicit val ec = system.dispatcher
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val committerSettings = CommitterSettings(system)

  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("192.168.0.29:39092")
      .withGroupId("akka-kafka-test1")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  val control = Consumer
    .committableSource(consumerSettings, Subscriptions.topics("gta_events"))
    .mapAsync(10) { msg =>
      Future(println(msg.record.offset() + ": " + msg.record.value)).map(_ => msg.committableOffset)
    }
    .map { msg =>
      val offset = msg.partitionOffset.offset
      if (offset % 2 == 0) {
        throw new RuntimeException("Please, don't swallow me!")
      } else {
        msg
      }
    }
    .via(Committer.flow(committerSettings.withMaxBatch(1)))
    .toMat(Sink.seq)(Keep.both)
    .mapMaterializedValue(DrainingControl.apply)
    .run()
}


