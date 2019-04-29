import utest._

object HelloSpec extends TestSuite {
  override def tests: Tests = Tests {
    'test {
      assert("Hello".startsWith("H"))
    }
  }
}