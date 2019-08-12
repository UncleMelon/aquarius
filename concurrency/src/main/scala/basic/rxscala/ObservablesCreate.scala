package basic.rxscala

import rx.lang.scala._
import basic.log

/**
* 
* @author matthew_wu
* @since 2019-07-09 11:48
*/
object ObservablesCreate extends App {

  val vms = Observable.apply[String] { obs =>
    obs.onNext("JVM")
    obs.onNext("DartVM")
    obs.onNext("V8")
    obs.onCompleted()
    Subscription()
  }

  log(s"About to subscribe")
  vms.subscribe(log _, e => log(s"oops - $e"), () => log("Done!"))
  log(s"Subscription returned")
}
