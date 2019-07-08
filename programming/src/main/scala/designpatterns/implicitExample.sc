//类型类
//类型类是一种非常灵活的设计模式，可以把类型的定义和行为进行分离，让扩展类行为变得非常方便。
trait Increasable[T] {
  def inc(t: T): T
}

object Increasable {

  implicit object IncreasableInt extends Increasable[Int] {
    def inc(t: Int): Int = t + 1
  }

  implicit object IncreasableString extends Increasable[String] {
    def inc(t: String): String = t + t
  }

}

def inc[T: Increasable](list: List[T]): List[T] = {
  val ev = implicitly[Increasable[T]]
  list.map(ev.inc)
}

import Increasable._

inc(List(1, 2, 3))
inc(List("z", "a", "b"))