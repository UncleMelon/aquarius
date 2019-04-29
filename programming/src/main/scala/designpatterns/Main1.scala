package designpatterns

import scala.reflect.ClassTag

object Main1 extends App {
  import scala.reflect.runtime.universe._

  trait Animal
  case class Rabbit(name: String) extends Animal
  case class Fox(name: String) extends Animal

  trait FeedGuide[T <: Animal] {
    def feed(animal: T): Unit
  }

  class RabbitGuide extends FeedGuide[Rabbit] {
    override def feed(animal: Rabbit): Unit = println("rabbit")
  }

  class FoxGuide extends FeedGuide[Fox] {
    override def feed(animal: Fox): Unit = println("fox")
  }

  //todo: 如何获取T的类型
  def register[T <: Animal: ClassTag](feedGuide: FeedGuide[T]): Unit = {
    val proto = implicitly[ClassTag[T]].runtimeClass
    val loginClass = proto
    println(s"${loginClass.getName} is registered!!!")
  }

  register[Rabbit](new RabbitGuide)
  register[Fox](new FoxGuide)
}
