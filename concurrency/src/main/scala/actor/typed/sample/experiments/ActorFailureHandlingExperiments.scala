package actor.typed.sample.experiments

import akka.actor.typed._
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

/**
 *
 * @author matthew_wu
 * @since 2019-08-13 17:19
 */
object ActorFailureHandlingExperiments extends App {
  val testSystem = ActorSystem(SupervisingActor(), "supervising-actor")
  testSystem ! "failChild"
}

object SupervisingActor {
  def apply(): Behavior[String] = Behaviors.setup(context => new SupervisingActor(context))
}

class SupervisingActor(context: ActorContext[String]) extends AbstractBehavior[String] {
  private val child = context.spawn(
    Behaviors.supervise(SupervisedActor()).onFailure(SupervisorStrategy.restart),
    name = "supervised-actor")

  override def onMessage(msg: String): Behavior[String] = {
    msg match {
      case "failChild" =>
        child ! "fail"
        this
    }
  }
}

object SupervisedActor {
  def apply(): Behavior[String] = Behaviors.setup(_ => new SupervisedActor)
}

class SupervisedActor extends AbstractBehavior[String] {
  println("supervised actor started")

  override def onMessage(msg: String): Behavior[String] = {
    msg match {
      case "fail" =>
        println("supervised actor fails now")
        throw new Exception("I failed!")
    }
  }

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PreRestart =>
      println("supervised actor will be restarted")
      this
    case PostStop =>
      println("supervised actor stopped")
      this
  }
}
