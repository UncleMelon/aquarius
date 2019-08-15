package actor.typed.sample.experiments

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior, PostStop, Signal}

/**
 *
 * @author matthew_wu
 * @since 2019-08-13 17:00
 */
object ActorStopExperiments extends App {
  val testSystem = ActorSystem(StartStopActor1(), "first")
  testSystem ! "stop"
}

object StartStopActor1 {
  def apply(): Behavior[String] = Behaviors.setup(context => new StartStopActor1(context))
}

class StartStopActor1(context: ActorContext[String]) extends AbstractBehavior[String] {
  println("first started")
  context.spawn(StartStopActor2(), "second")

  override def onMessage(msg: String): Behavior[String] = {
    msg match {
      case "stop" => Behaviors.stopped
    }
  }

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PostStop =>
    println("first stopped")
    this
  }
}


object StartStopActor2 {
  def apply(): Behavior[String] = Behaviors.setup(_ => new StartStopActor2)
}

class StartStopActor2 extends AbstractBehavior[String] {
  println("second started")

  override def onMessage(msg: String): Behavior[String] =
    // no messages handled by this actor
    Behaviors.unhandled

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PostStop =>
      println("second stop")
      this
  }

}
