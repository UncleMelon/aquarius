import utest.{TestSuite, Tests}

import utest._

object HelloTests extends TestSuite {

  override def tests = Tests {
    'startWithH - {
      assert("hello".startsWith("H"))
    }
  }
}