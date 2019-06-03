package basic

object LazyValsCreate extends App {
  lazy val obj = new AnyRef
  lazy val non = s"made by ${Thread.currentThread.getName}"

  execute {
    log(s"EC sees obj = $obj")
    log(s"EC sess non = $non")
  }

  log(s"Main sees obj = $obj")
  log(s"Main sees non = $non")
  Thread.sleep(500)
}
