package basic.rxscala

import rx.lang.scala._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import basic.log

/**
* 
* @author matthew_wu
* @since 2019-07-09 18:14
*/
object ObservablesCreateFuture extends App {
  val f = Future { "Back to the Future(s)" }
  val o = Observable.apply[String] { obs =>
    f foreach { case s => obs.onNext(s); obs.onCompleted() }
    f.failed foreach {case t => obs.onError(t) }
    Subscription()
  }
  o.subscribe(log _)
}