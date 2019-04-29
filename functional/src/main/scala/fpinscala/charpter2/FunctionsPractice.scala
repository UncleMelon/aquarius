package fpinscala.charpter2

object FunctionsPractice {

  /**
    * 爬台阶的问题
    * @param n
    * @return
    */
  def fib_tailrec(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, prev: Int, cur: Int): Int = {
      if (n == 0) prev
      else go(n-1, cur, prev + cur)
    }
    go(n, 0, 1)
  }

  def fib(n: Int): Int = {
    // not tail recursive
    if (n < 2) n
    else fib(n-1) + fib(n-2)
  }


  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int = {
      if (n == 0) acc
      else go(n - 1, n * acc)
    }
    go(n, 1)
  }

  /**
    * HOF  => High Order Function, 接收函数f作为入参
    * @param name
    * @param n
    * @param f
    */
  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d"
    msg.format(name, n, f(n))
  }

  /**
    * 多态函数, 也称为泛型函数
    */
  def findFirst[A] (as: Array[A], p: A => Boolean) = {
    def loop(n: Int): Int = {
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)
    }
    loop(0)
  }

  def isSorted[A](as: Array[A], gt: (A, A) => Boolean) = {
    def loop(n: Int): Boolean = {
      if(n >= as.length - 1) true
      else if (!gt(as(n), as(n+1))) false
      else loop(n + 1)
    }
    loop(0)
  }

  def isSorted_fun[A](as: Array[A], ordered: (A,A) => Boolean) = {
    val sortedArray = as.sortWith(ordered)
    as.zip(sortedArray).forall(x => x._1 == x._2)
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) =
    (a: A) => ((b: B) => f(a, b))

  def uncurry[A,B,C](f: A => B => C): (A,B) => C =
    (a: A, b: B) => f(a)(b)

  def compose[A,B,C](f: B => C, g: A => B): A => C =
    (a: A) => f(g(a))

  def main(args: Array[String]): Unit = {
    println(fib(5))
    println(fib_tailrec(5))
    println(factorial(3))
    println(formatResult("factorial", 7, factorial))
    println(findFirst(Array("a", "b","abc", "1"), (x: String)  => x == "abc"))
    println(isSorted(Array(1,2,3,5,4), (x:Int, y: Int) => x < y))
  }
}
