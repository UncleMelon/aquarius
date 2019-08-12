package myakka

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern._

import scala.concurrent.ExecutionContext.Implicits.global

/**
* 
* @author matthew_wu
* @since 2019-07-16 10:54
*/
class Pongy extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case "ping" =>
      log.info("Got a ping -- ponging back!")
      sender ! "pong"
      context.stop(self)
  }

  override def postStop(): Unit = log.info("pongy going down")
}

class Pingy extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case pongyRef: ActorRef =>
    implicit val timeout = Timeout(2 seconds)
    val f = pongyRef ? "ping"
    pipe(f) to sender()
  }
}

class Master extends Actor {
  val log = Logging(context.system, this)
  val pingy = ourSystem.actorOf(Props[Pingy], "pingy")
  val pongy = ourSystem.actorOf(Props[Pongy], "pongy")
  def receive = {
    case "start" =>
      pingy ! pongy
    case "pong" =>
      log.info("got a pong back!")
      context.stop(self)
  }
  override def postStop() = log.info("master going down")
}

object CommunicatingAsk extends App {
  val master = ourSystem.actorOf(Props[Master], "master")
  master ! "start"
  Thread.sleep(1000)
  ourSystem.stop(master)
}