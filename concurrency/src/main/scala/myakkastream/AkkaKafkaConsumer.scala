package myakkastream

import akka.actor.ActorSystem
import akka.kafka._
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.scaladsl.{Committer, Consumer, Producer}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
* 
* @author matthew_wu
* @since 2019-07-17 17:17
*/
object AkkaKafkaConsumer extends App {
  implicit val system = ActorSystem("kafka-test")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val committerSettings = CommitterSettings(system)
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("192.168.0.29:39092")
      .withGroupId("akka-kafka-test")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  val producerSettings =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers("")
  val control = Consumer
    .committableSource(consumerSettings, Subscriptions.topics(""))
    .map { msg =>
      ProducerMessage.single(
        new ProducerRecord("", msg.record.key, msg.record.value),
        passThrough = msg.committableOffset
      )
    }
    .via(Producer.flexiFlow(producerSettings))
    .map(_.passThrough)
    .toMat(Committer.sink(committerSettings))(Keep.both)
    .mapMaterializedValue(DrainingControl.apply)
    .run()

  sys.ShutdownHookThread {
    Await.result(control.drainAndShutdown(), 5.seconds)
  }
}

object AkkaKafkaConsumer1 extends App {
  implicit val system = ActorSystem("kafka-test")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val committerSettings = CommitterSettings(system)
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("192.168.0.29:39092")
      .withGroupId("akka-kafka-test")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    Consumer.plainSource(consumerSettings, Subscriptions.topics("gta_events"))
      .mapAsync(1){ msg => Future(msg.offset() + ": "+ msg.value())}
      .throttle(1, 1 seconds)
      .runWith(Sink.foreach(println))
}

object AkkaKafkaConsumer2 extends App {
  implicit val system = ActorSystem("kafka-test")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val committerSettings = CommitterSettings(system)
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("192.168.0.29:39092")
      .withGroupId("akka-kafka-test")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    Consumer
      .committableSource(consumerSettings, Subscriptions.topics("gta_events"))
      .mapAsync(10) { msg =>
        Future(println(msg.record.offset() + ": " + msg.record.value)).map(_ => msg.committableOffset)
      }
      .via(Committer.flow(committerSettings.withMaxBatch(1)))
      .toMat(Sink.seq)(Keep.both)
      .mapMaterializedValue(DrainingControl.apply)
      .run()
}

object AkkaKafkaConsumer3 extends App {
  implicit val system = ActorSystem("kafka-test")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val committerSettings = CommitterSettings(system)
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("192.168.0.29:39092")
      .withGroupId("akka-kafka-test")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  Consumer.plainSource(consumerSettings, Subscriptions.topics("gta_events"))
    .mapAsync(1){ msg => Future(msg.offset() + ": "+ msg.value())}
    .throttle(1, 1 seconds)
    .runWith(Sink.foreach(println))
}