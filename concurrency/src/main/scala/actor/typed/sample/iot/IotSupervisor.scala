package actor.typed.sample.iot

import akka.actor.typed.{Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

/**
  *
  * @author matthew_wu
  * @since 2019-08-13 18:00
  */
object IotSupervisor {
  def apply(): Behavior[Nothing] = Behaviors.setup[Nothing](context => new IotSupervisor(context))
}


class IotSupervisor(context: ActorContext[Nothing]) extends AbstractBehavior[Nothing] {
  context.log.info("IoT Application started")

  override def onMessage(msg: Nothing): Behavior[Nothing] = Behaviors.unhandled

  override def onSignal: PartialFunction[Signal, Behavior[Nothing]] = {
    case PostStop =>
      context.log.info("IopT Application stoppted")
      this
  }
}