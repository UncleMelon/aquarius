package myakkastream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.hdfs._
import akka.stream.alpakka.hdfs.scaladsl.HdfsFlow
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem

/**
  *
  * @author matthew_wu
  * @since 2019-08-15 18:01
  */
object AkkaStreamHdfs extends App {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()
  implicit val ec = system.dispatcher

  val conf = new Configuration()
  conf.set("fs.defaultFS", "hdfs://192.168.0.22:8020")
  val fs: FileSystem = FileSystem.get(conf)


  val pathGenerator =
    FilePathGenerator(
      (rotationCount: Long, timestamp: Long) => s"/tmp/alpakka/$rotationCount-$timestamp"
    )
  val settings =
    HdfsWritingSettings()
      .withOverwrite(true)
      .withNewLine(true)
      .withPathGenerator(pathGenerator)

  val messagesFromKafka = List(
    KafkaMessage(Book("Akka Concurrency"), KafkaOffset(0)),
    KafkaMessage(Book("Akka in Action"), KafkaOffset(1)),
    KafkaMessage(Book("Effective Akka"), KafkaOffset(2)),
    KafkaMessage(Book("Learning Scala"), KafkaOffset(3)),
    KafkaMessage(Book("Scala Puzzlers"), KafkaOffset(4)),
    KafkaMessage(Book("Scala for Spark in Production"), KafkaOffset(5))
  )

  var committedOffsets = List[KafkaOffset]()

  def commitToKafka(offset: KafkaOffset): Unit =
    committedOffsets = committedOffsets :+ offset


  val resF = Source(messagesFromKafka)
    .map { kafkaMessage: KafkaMessage =>
      val book = kafkaMessage.book
      // Transform message so that we can write to hdfs
      HdfsWriteMessage(ByteString(book.title), kafkaMessage.offset)
    }
    .via(
      HdfsFlow.dataWithPassThrough[KafkaOffset](
        fs,
        SyncStrategy.count(50),
        RotationStrategy.count(4),
        settings
      )
    )
    .map { message =>
      message match {
        case WrittenMessage(passThrough, _) =>
          commitToKafka(passThrough)
        case _ => ()
      }
      message
    }
    .collect {
      case rm: RotationMessage => println(rm)
    }
    .runWith(Sink.seq)

}

case class Book(title: String)
case class KafkaOffset(offset: Int)
case class KafkaMessage(book: Book, offset: KafkaOffset)