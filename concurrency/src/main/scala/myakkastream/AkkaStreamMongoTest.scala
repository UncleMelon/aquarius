package myakkastream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future


/**
* 
* @author matthew_wu
* @since 2019-07-19 23:38
*/
object AkkaStreamMongoTest extends App {
  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()
  implicit val ec = system.dispatcher
  val mapper: String => Future[Seq[String]] = (userId: String) =>
    Future(MongoService.findUserTeams(userId))
  Source(List("5be24d5c530c55000172cb78"))
    .mapAsync[Seq[String]](1)(mapper)
    .runWith(Sink.foreach(println))
}
