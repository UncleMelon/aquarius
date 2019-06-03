import scala.concurrent.ExecutionContext

package object basic {

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run(): Unit = body
    }
    t.start()
    t
  }

  def execute(body: => Unit): Unit = ExecutionContext.global.execute(
    () => body
  )

  def log(msg: String): Unit = {
    println(s"${Thread.currentThread().getName}: $msg")
  }
}
