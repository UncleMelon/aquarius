package myakkastream
import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{FileIO, Flow, Keep, Sink, Source}
import akka.stream.{ActorMaterializer, IOResult}
import akka.util.ByteString

import scala.concurrent.Future
import scala.concurrent.duration._

/**
* 
* @author matthew_wu
* @since 2019-07-17 14:29
*/

object QuickStart1 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val done = source.runForeach(i => println(i))
  done.onComplete(_ => system.terminate())
}

object QuickStart2 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
  val result = factorials.map(num => ByteString(s"$num\n")).runWith(FileIO.toPath(Paths.get("factorials.txt")))
  result.onComplete(_ => system.terminate())
}

/**
 * Reusable Pieces
 **/
object QuickStart3 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
  val result = factorials.map(_.toString).runWith(linkSink("factorials2.txt"))

  def linkSink(filename: String): Sink[String, Future[IOResult]] =
    Flow[String].map(s => ByteString(s + "\n")).toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)
  result.onComplete(_ => system.terminate())
}

object QuickStart4 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)
  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
  val result = factorials.zipWith(Source(0 to 100))((num, idx) => s"$idx != $num")
      .throttle(1, 1 seconds)
      .runForeach(println)
  result.onComplete(_ => system.terminate())
}

object QuickStart5 extends App {
  implicit val system = ActorSystem("reactive-tweets")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  final case class Author(handle: String)
  final case class Hashtag(name: String)
  final case class Tweet(author: Author, timestamp: Long, body: String) {
    def hashtags: Set[Hashtag] =
      body
      .split(" ")
      .collect {
        case t if t.startsWith("#") => Hashtag(t.replaceAll("[^#\\w]", ""))
      }
      .toSet
  }

  val akkaTag = Hashtag("#akka")

  val tweets: Source[Tweet, NotUsed] = Source(
    Tweet(Author("rolandkuhn"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("patriknw"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("bantonsson"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("drewhk"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("ktosopl"), System.currentTimeMillis, "#akka on the rocks!") ::
      Tweet(Author("mmartynas"), System.currentTimeMillis, "wow #akka !") ::
      Tweet(Author("akkateam"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("bananaman"), System.currentTimeMillis, "#bananas rock!") ::
      Tweet(Author("appleman"), System.currentTimeMillis, "#apples rock!") ::
      Tweet(Author("drama"), System.currentTimeMillis, "we compared #apples to #oranges!") ::
      Nil)

    tweets
    .map(_.hashtags)
    .reduce(_ ++ _)
    .mapConcat(identity)
    .map(_.name.toUpperCase)
    .runWith(Sink.foreach(println))
}