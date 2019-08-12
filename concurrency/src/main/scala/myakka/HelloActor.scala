package myakka

import akka.actor._
import akka.event.Logging

/**
* 
* @author matthew_wu
* @since 2019-07-13 15:02
*/
class HelloActor(val hello: String) extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case `hello` =>
      log.info(s"Received a '$hello'... $hello")
    case msg =>
      log.info(s"Unexpected message '$msg'")
      context.stop(self)
  }
}

object HelloActor {
  def props(hello: String) = Props(new HelloActor(hello))
  def propsAlt(hello: String) = Props(classOf[HelloActor], hello)
}

object ActorsCreate extends App {
  lazy val system = ActorSystem("OurExampleSystem")
  val hiActor = system.actorOf(HelloActor.props("hi"), "greeter")
  hiActor ! "hi"
  Thread.sleep(1000)
  hiActor ! "hola"
  Thread.sleep(1000)
  system.stop(hiActor)
}
