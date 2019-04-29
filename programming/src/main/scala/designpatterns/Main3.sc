final case class Apple(weight: Double) extends AnyVal
final case class Person(height: Double) extends AnyVal

trait CanCompare[A] {
  def compare1(a1: A, a2: A): Double
}
object CanCompare {
  implicit val appleCanCompare = new CanCompare[Apple] {
    override def compare1(a1: Apple, a2: Apple) = a1.weight - a2.weight
  }

  implicit val personCanCompare = new CanCompare[Person] {
    override def compare1(a1: Person, a2: Person) = a1.height - a2.height
  }
}

final case class CanCompareOps[A](private val a1: A) extends AnyVal {
  def compare1(a2: A)(implicit ev: CanCompare[A]): Double
  = implicitly[CanCompare[A]].compare1(a1, a2)
}

object syntax {
  implicit def canCompareSyntax[A: CanCompare](a: A) = CanCompareOps(a)
}

import CanCompare._
import syntax._

Apple(1).compare1(Apple(2))