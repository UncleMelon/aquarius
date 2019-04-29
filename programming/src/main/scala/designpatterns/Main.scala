package designpatterns

object Main extends App {

  import scala.reflect.runtime.universe._

  trait Animal
  case class Rabbit(name: String) extends Animal
  case class Fox(name: String) extends Animal

  val list: Seq[Animal] = List(Rabbit("Judy"), Fox("Nick"))

  //todo: 如何打印具体类的名称？
  //def printClassName[T <: Animal](animal: T): Unit = ???
  //def printClassName[T <: Animal :TypeTag](animal: T): Unit = println(implicitly[TypeTag[T]])
  def printClassName[T <: Animal :TypeTag](animal: T): Unit = println(animal.getClass)

  list.foreach(printClassName)
}
