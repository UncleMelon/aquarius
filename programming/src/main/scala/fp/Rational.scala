package fp

class Rational(x: Int, y: Int) {
  require(y != 0, "The denominator must be nonzero!")

  def this(x: Int) = this(x, 1)

  def numer = x / g
  def denom = y / g

  def +(that: Rational) =
    new Rational(this.numer * that.denom + this.denom * that.numer, this.denom * that.denom)

  def negative = new Rational(-this.numer, this.denom)

  def -(that: Rational) =
    this + that.negative

  def <(that: Rational) = this.numer * that.denom - this.denom * that.numer < 0

  def max(that: Rational): Rational = if (this < that) that else this

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  override def toString: String = s"${numer}/${denom}"
}


object Rational extends App {
  val x = new Rational(1, 3)
  val y = new Rational(3, 5)
  val z = new Rational(5, 7)

  val simple = new Rational(4, 8)

  println(x - y)
  println(simple)
  println(x < simple)
}