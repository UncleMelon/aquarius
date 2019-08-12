package myakkastream

import java.util.concurrent.TimeoutException

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.kafka.scaladsl.Consumer
import akka.pattern.after
import akka.stream.ActorMaterializer
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax._
import io.circe.{Decoder, ObjectEncoder}
import myakkastream.Server.StandardResponse

import scala.collection.immutable.Seq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Server {

  case class StandardResponse(status: String, code: Int, data: Option[String] = None)

  object StandardResponse {
    implicit val decodeB: Decoder[StandardResponse] = deriveDecoder[StandardResponse]
    implicit val encodeB: ObjectEncoder[StandardResponse] = deriveEncoder[StandardResponse].mapJsonObject(_.filter {
      case ("data", value) => !value.isNull
      case _               => true
    })
  }

  def apply(streams: Consumer.DrainingControl[Seq[Done]]): Server = new Server(streams)
}

class Server(streams: Consumer.DrainingControl[Seq[Done]]) {

  implicit val system       = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  private val service       = new Service(streams)
  private val OK            = 200
  private val ERROR         = 503

  def start() = {

    val route = path("health") {
      get {
        onComplete(service.healthCheck()) {
          case Success(_) => complete(StandardResponse("OUT_OF_SERVICE", ERROR).asJson.noSpaces)
          case Failure(_) => complete(StandardResponse("UP", OK).asJson.noSpaces)
        }
      }
    }

    Http().bindAndHandle(route, "0.0.0.0", 8080)
  }

  def stop() = {
    Http().shutdownAllConnectionPools().andThen { case _ => system.terminate() }
    complete("Shutting down app")
  }
}

class Service(streams: Consumer.DrainingControl[Seq[Done]])(implicit system: ActorSystem) {

  implicit val timeout = 1 second

  def healthCheck(): Future[Done] = {
    implicit class FutureExtensions[T](f: Future[T]) {
      def withTimeout(timeout: => Throwable)(implicit duration: FiniteDuration, system: ActorSystem): Future[T] = {
        Future firstCompletedOf Seq(f, after(duration, system.scheduler)(Future.failed(timeout)))
      }
    }
    streams.isShutdown withTimeout new TimeoutException("Future timed out!")
  }
}



object Test extends App {
  import akka.pattern.after

  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val system = ActorSystem("theSystem")
  implicit val timeout = 1 second

  lazy val f = Future { Thread.sleep(2000); true }

  f withTimeout new TimeoutException("Future timed out!") onComplete {
    case Success(x) => println(x)
    case Failure(error) => println(error)
  }

  implicit class FutureExtensions[T](f: Future[T]) {
    def withTimeout(timeout: => Throwable)(implicit duration: FiniteDuration, system: ActorSystem): Future[T] = {
      Future firstCompletedOf Seq(f, after(duration, system.scheduler)(Future.failed(timeout)))
    }
  }
}