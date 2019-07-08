package basic

import scala.concurrent.{CancellationException, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object PromisesCancellation extends App {
  type Cancellable[T] = (Promise[Unit], Future[T])

  def cancellable[T](b: Future[Unit] => T): Cancellable[T] = {
    val cancel = Promise[Unit]
    val f = Future {
      val r = b(cancel.future)
      //此处的检测，避免竞态条件，即客户端成功发出了取消请求，同时future计算也成功完善了Future对象
      if (!cancel.tryFailure(new Exception)) {
        throw new CancellationException("ssssss")
      }
      r
    }
    (cancel, f)
  }

  val (cancel, value) =  cancellable { cancel =>
    var i = 0
    while (i < 5) {
      if (cancel.isCompleted) throw new CancellationException("tttt")
      Thread.sleep(500)
      log(s"$i: working")
      i += 1
    }
    "resulting value"
  }

  Thread.sleep(1500)
  cancel trySuccess ()
  log("computation cancelled!")
  value onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  }
  Thread.sleep(2000)
}
