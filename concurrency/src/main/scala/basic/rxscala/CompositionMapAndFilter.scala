package basic.rxscala

import rx.lang.scala._
import basic.log
import scala.concurrent.duration._

/**
* 
* @author matthew_wu
* @since 2019-07-10 18:22
*/
object CompositionMapAndFilter extends App {
  val odds = Observable.interval(0.5 seconds)
    .filter(_ % 2 == 1)
    .map(n => s"num $n")
    .take(5)
  odds.subscribe(
    log _, e => log(s"unexpected $e"), () => log("no more odds"))
  Thread.sleep(5000)
}
