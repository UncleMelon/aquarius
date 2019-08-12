package myakka

import akka.actor._
import akka.event.Logging

/**
* 
* @author matthew_wu
* @since 2019-07-13 15:29
*/
class DeafActor extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = PartialFunction.empty

  override def unhandled(message: Any): Unit = message match {
    case msg: String => log.info(s"I do not hear $msg")
    case msg => super.unhandled(msg)
  }

}

object ActorsUnhandled extends App {
  lazy val system = ActorSystem("OurExampleSystem")
  val deafActor = system.actorOf(Props[DeafActor], "deafy")
  deafActor ! "hi"
  Thread.sleep(1000)
  deafActor ! 1234
  Thread.sleep(1000)
  system.stop(deafActor)
}
