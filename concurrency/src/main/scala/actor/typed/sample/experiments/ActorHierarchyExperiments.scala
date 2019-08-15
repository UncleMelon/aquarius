package actor.typed.sample.experiments

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior}

/**
* 
* @author matthew_wu
* @since 2019-08-13 16:39
*/
object ActorHierarchyExperiments extends App {
  val testSystem = ActorSystem(Main(), "testSystem")
  testSystem ! "start"
}

object Main {
  def apply(): Behavior[String] = Behaviors.setup(context => new Main(context))
}

class Main(context: ActorContext[String]) extends AbstractBehavior[String] {

  override def onMessage(msg: String): Behavior[String] = {
    msg match {
      case "start" =>
        val firstRef = context.spawn(PrintMyActorRefActor(), "first-actor")
        println(s"First: $firstRef")
        firstRef ! "printit"
        this
    }
  }
}

object PrintMyActorRefActor {
  def apply(): Behavior[String] = Behaviors.setup(context => new PrintMyActorRefActor(context))
}

class PrintMyActorRefActor(context: ActorContext[String]) extends AbstractBehavior[String] {

  override def onMessage(msg: String): Behavior[String] = {
    msg match {
      case "printit" =>
        val secondRef = context.spawn(Behavior.empty[String], "second-actor")
        println(s"Second: $secondRef")
        this
    }
  }
}