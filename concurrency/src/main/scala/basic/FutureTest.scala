package basic

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object FutureTest extends App {

  val f: Future[Int] = Future {
    Thread.sleep(10)
    1/0
    1
  }
  val recovered: Future[Int] = f.recover { case e: ArithmeticException => 0 }
  val a = Await.result(recovered, Duration.Inf)
  println(a)


  val f1: Future[Int] = Future {
    Thread.sleep(10)
    1/0
  }

  Await.result(f1, Duration.Inf)
  f1.onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(exception.getMessage)
  }

}
