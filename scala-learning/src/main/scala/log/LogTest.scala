package log

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.scala.{Logger, Logging}

class LogTest extends Logging {

  def doStuff(): Unit = {
    logger.info("Doing stuff")
  }

  def doStuffWithLevel(level: Level): Unit = {
      logger(level, "Doing stuff with arbitrary level")
  }
}

object LogTest {
  def main(args: Array[String]): Unit = {
    val logTest = new LogTest()
    logTest.doStuff()
    logTest.doStuffWithLevel(Level.ERROR)
  }
}
