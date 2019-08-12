package exercises

class Fibonacci {

  def recursion(n: BigInt): BigInt = {
    @annotation.tailrec
    def loop(n: BigInt, acc1: BigInt, acc2: BigInt): BigInt =
      if (n == 0) acc1
      else loop(n - 1, acc2, acc1 + acc2)
    loop(n, 0, 1)
  }
}

object Fibonacci extends App {

  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  val fib = new Fibonacci()
  //F(100)
  println(fib.recursion(100))
  //到达1000位的数
  (0 to 100000).find(i => digits(fib.recursion(i)) == 1000).map(println(_))

  def digits(n: BigInt): Int = {
    @annotation.tailrec
    def loop(n: BigInt, acc: Int): Int =
      if (n < 10) acc
      else loop(n / 10, acc + 1)
    loop(n, 1)
  }
}
