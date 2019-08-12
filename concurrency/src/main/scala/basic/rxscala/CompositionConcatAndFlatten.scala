package basic.rxscala

import rx.lang.scala.Observable

import scala.concurrent.Future
import scala.concurrent.blocking
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source
import scala.concurrent.duration._
import basic.log

/**
* 
* @author matthew_wu
* @since 2019-07-11 17:07
*/
object CompositionConcatAndFlatten extends App {

  def fetchQuote() = Future {
    blocking {
      val url = "http://quotes.stormconsultancy.co.uk/random.json"
      Source.fromURL(url).getLines.mkString
    }
  }

  def fetchQuoteObservable(): Observable[String] = Observable.from(fetchQuote())

  def quotes: Observable[Observable[String]] = Observable.interval(0.5 seconds).take(5).map {
    n => fetchQuoteObservable().map(txt => s"$n) $txt")
  }

  log(s"Using concat")
  quotes.concat.subscribe(log _)

  Thread.sleep(6000)

  log(s"Now using flatten")
  quotes.flatten.subscribe(log _)

  Thread.sleep(6000)
}

// There is always one more bug.
object CompositionRetry extends App {
  import rx.lang.scala._
  import scala.io.Source
  import Observable._

  def randomQuote = Observable.apply[String] { obs =>
    val url = "http://quotes.stormconsultancy.co.uk/random.json"
    obs.onNext("test1")
    obs.onNext("test2")
    obs.onNext("test3")
    obs.onNext("test4")
    obs.onNext("test5")
    obs.onNext(Source.fromURL(url).getLines.mkString)
    obs.onCompleted()
    Subscription()
  }

  def errorMessage = just("Retrying...") ++ error(new Exception)

  def shortQuote = for {
    txt     <- randomQuote
    message <- if (txt.length < 100) just(txt) else errorMessage
  } yield message

  shortQuote.retry(5).subscribe(log _, e => log(s"too long - $e"), () => log("done!"))
}


object CompositionScan extends App {
  import rx.lang.scala._

  CompositionRetry.shortQuote.retry.repeat.take(100).scan(0) {
    (n, q) => if (q == "Retrying...") n + 1 else n
  } subscribe(n => log(s"$n / 100"))
}


object CompositionErrors extends App {
  import rx.lang.scala._

  val status = Observable[String] { sub =>
    sub.onNext("ok")
    sub.onNext("still ok")
    sub.onError(new Exception("very bad"))
  }

  val fixedStatus = status.onErrorReturn(e => e.getMessage)
  fixedStatus.subscribe(log _)

  val continuedStatus = status.onErrorResumeNext(e => Observable.just("better", "much better"))
  continuedStatus.subscribe(log _)

}