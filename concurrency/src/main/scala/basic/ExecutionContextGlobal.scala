package basic

import scala.concurrent.{ExecutionContext, forkjoin}

object ExecutionContextGlobal extends App {
  val ectx = ExecutionContext.global
  ectx.execute(new Runnable {
    override def run(): Unit = log("Running on the execution context.")
  })

  Thread.sleep(500)
}


object ExecutionContextCreate extends App {
  val pool = new forkjoin.ForkJoinPool(2)
  val extx = ExecutionContext.fromExecutorService(pool)
  extx.execute(() => log("Running on the execution context again."))
  Thread.sleep(500)
}

object ExecutionContextSleep extends App {
  for (i <- 0 until 32) execute {
    Thread.sleep(2000)
    log(s"Task $i completed.")
  }
  Thread.sleep(20000)
}