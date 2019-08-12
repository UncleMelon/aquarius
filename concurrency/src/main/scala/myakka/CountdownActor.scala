package myakka

import akka.actor._
import akka.event.Logging

/**
* 
* @author matthew_wu
* @since 2019-07-13 15:02
*/
class CountdownActor extends Actor {
  val log = Logging(context.system, this)
  var n = 10

  def counting: Receive = {
    case "count" =>
      n -= 1
      log.info(s"n = $n")
      if (n == 0)  context.become(done)
  }

  def done: Receive = PartialFunction.empty

  override def receive: Receive = counting
}


object CountdownActor extends App {
  lazy val system = ActorSystem("OurExampleSystem")
  val countdownActor = system.actorOf(Props[CountdownActor])
  for (i <- 0 until 20) countdownActor ! "count"
  Thread.sleep(1000)
  system.stop(countdownActor)
}
