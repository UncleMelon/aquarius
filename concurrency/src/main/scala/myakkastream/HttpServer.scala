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
import myakkastream.HttpServer.StandardResponse

import scala.collection.immutable.Seq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object HttpServer {

  case class StandardResponse(status: String, code: Int, data: Option[String] = None)

  object StandardResponse {
    implicit val decodeB: Decoder[StandardResponse] = deriveDecoder[StandardResponse]
    implicit val encodeB: ObjectEncoder[StandardResponse] = deriveEncoder[StandardResponse].mapJsonObject(_.filter {
      case ("data", value) => !value.isNull
      case _               => true
    })
  }

  def apply(streams: Seq[(String, Consumer.DrainingControl[Done])])(
    implicit system: ActorSystem,
    materializer: ActorMaterializer): HttpServer = new HttpServer(streams)
}

class HttpServer(streams: Seq[(String, Consumer.DrainingControl[Done])])(
  implicit system: ActorSystem,
  materializer: ActorMaterializer) {

  private val logger  = org.slf4j.LoggerFactory.getLogger(this.getClass)
  private val service = new Service(streams)
  private val OK      = 200
  private val ERROR   = 503

  def start() = {

    val route = path("health") {
      get {
        onComplete(service.healthCheck()) {
          case Success(_) => complete(StandardResponse("UP", OK).asJson.noSpaces)
          case Failure(v) =>
            complete(StandardResponse(s"Data Flow ${v.getMessage} is OUT_OF_SERVICE", ERROR).asJson.noSpaces)
        }
      }
    }
    Http().bindAndHandle(route, "0.0.0.0", 8080)
    logger.info(s"server started at 8080")
  }

  def stop() = {
    Http().shutdownAllConnectionPools().andThen { case _ => system.terminate() }
    complete("Shutting down app")
  }
}

class Service(streams: Seq[(String, Consumer.DrainingControl[Done])])(implicit system: ActorSystem) {

  private val logger   = org.slf4j.LoggerFactory.getLogger(this.getClass)
  implicit val timeout = 1 second

  def healthCheck(): Future[Seq[Done]] = {
    val futures = streams.map {
      case (_, control) => control.isShutdown.withTimeout(new TimeoutException("Future timed out!"))
    }
    Future
      .sequence(futures.map(_.transform(Try(_))))
      .map(_.zipWithIndex.collect {
        case (Success(_), index) => {
          val streamName = streams(index)._1
          logger.error(s"data flow $streamName is down...")
          throw new RuntimeException(streamName)
        }
        case (Failure(_), _) => Done
      })
  }

  implicit class FutureExtensions[T](f: Future[T]) {

    def withTimeout(timeout: => Throwable)(implicit duration: FiniteDuration, system: ActorSystem): Future[T] =
      Future.firstCompletedOf(Seq(f, after(duration, system.scheduler)(Future.failed(timeout))))
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