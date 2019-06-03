import fp.{Succ, Zero}
import utest._

object NatTest extends TestSuite {
  val one = new Succ(Zero)
  val two = new Succ(one)
  val three = new Succ(two)

  override def tests: Tests = Tests {
    'test {
      println(Zero)
      println(Zero.successor + Zero.successor)
      println(s"four minus two is ==> ${new Succ(new Succ(new Succ(one))) - new Succ(one)}" )
    }
  }
}