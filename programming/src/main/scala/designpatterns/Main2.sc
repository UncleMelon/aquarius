
sealed abstract class Animal {
  def feed: String
}

final case class Rabbit(name: String) extends AnyVal {
  def feed: String = "rabbit"
}

final case class Fox(name: String) extends AnyVal {
  def feed: String = "fox"
}

trait CanRegister[A] {
  def register[A]: Unit
}

object CanRegister {
  implicit val rabbitCanRegister = new CanRegister[Rabbit] {
    override def register[A]: Unit = ???
  }
  implicit val foxCanRegister = new CanRegister[Fox] {
    override def register[A]: Unit = ???
  }
}

def register[A](a: A)(implicit ev: CanRegister[A]): Unit = ev.register


register(new Rabbit("ttt"))