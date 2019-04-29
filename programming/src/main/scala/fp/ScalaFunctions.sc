import scala.annotation.tailrec

def and(x: Boolean, y: Boolean): Boolean = if (x) y else false

def or(x: Boolean, y: Boolean): Boolean = if (x) true else y


def abs(x: Double) =  if (x >= 0) x else -x


def isGoodEnough(guess: Double, x: Double) =
  abs(guess * guess - x) / x < 0.001

def iterator(guess: Double, x: Double) =
  (x/guess + guess) / 2

def sqrtHelper(x: Double, guess: Double): Double = {
  if (isGoodEnough(guess, x)) guess
  else sqrtHelper(x, iterator(guess, x))
}

def sqrt(x: Double) = sqrtHelper(x, 1.0)

sqrt(1e-6)

@tailrec
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

def factorial(n: Int): BigInt = {
  @tailrec
  def facHelper(n: Int, acc: Int): BigInt =
    if (n == 0) acc else facHelper(n - 1, acc * n)
  facHelper(n, 1)
}


def factorialNoTail(n: Int): BigInt =
  if (n == 0) 1 else n * factorialNoTail(n - 1)

factorial(10)
factorialNoTail(10)

//Higher-Order Functions
def sum(a:Int, b:Int, f: Int => Int):Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}

sum(3, 5, x => x*x)

def countChange(money: Int, coins: List[Int]): Int = {
  if (money == 0) 1
  else if (money < 0) 0
  else if (money > 0 && coins.isEmpty) 0
  // whether the change contains the head of coins
  else countChange(money, coins.tail) + countChange(money - coins.head, coins)
}

countChange(4, List(1, 2))

//return functions
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumFun(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumFun(a + 1, b)
  }
  sumFun
}

def sumInts = sum(x => x)
def sumSquares = sum(x => x * x)

//currying
def sum1(f: Int => Int)(a: Int, b: Int): Int = {
  if(a > b) 0
  else f(a) + sum1(f)(a + 1, b)
}

def mapReduce(mapper: Int => Int, reducer: (Int, Int) => Int,
              unit: Int)(a: Int, b:Int): Int = {
  if (a > b) unit
  else reducer(mapper(a), mapReduce(mapper, reducer,unit)(a + 1, b))
}

mapReduce(x => x * x, (x, y) => x + y, 0)(3, 5)

def sumInts1(a: Int, b:Int) =
  mapReduce(x => x, (i, j) => i + j, 0)(a, b)

sumInts1(1, 5)

def product(f: Int => Int)(a: Int, b:Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

def product1(f: Int => Int)(a:Int, b:Int) =
  mapReduce(f, (i, j) => i * j, 1)(a, b)

product(x => x)(1, 5)
product1(x => x)(1, 5)

def factorial1(n: Int) = product1(x => x)(1, n)