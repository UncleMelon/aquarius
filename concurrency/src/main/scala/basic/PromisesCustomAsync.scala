package basic

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.{global => g}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object PromisesCustomAsync extends App {
  def myFuture[T](b: => T): Future[T] = {
    val p = Promise[T]
    g.execute(() => {
      try {
        p.success(b)
      } catch {
        case NonFatal(e) => p.failure(e)
      }
    })
    p.future
  }

  // The main difference is the onComplete callback gets called even if the future completes with a failure,
  // while foreach (and onSuccess) functions are called only in case of a successful result.
  val f = myFuture { "naa" + "na" * 8 + " Katamari Damacy!" + 1/0}
  f foreach {
    case text => log(text)
   }

  f onComplete {
    case Success(value) => println(value)
    case Failure(exception)  => println(exception.getMessage)
  }

  Thread.sleep(5000)
}
