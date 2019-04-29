package fparse

import fastparse.Parsed._

object IndentationGrammars extends App {
  import fastparse._
  def expr[_: P] = new Parser(indent = 0).expr
  def check(str: String, num: Int) = {
    val Parsed.Success(value, _) = parse(str, expr(_))
    assert(value == num)
  }
  check(
    """+
      | 1
      | 1
    """.stripMargin.trim,
    2
  )
  check(
    """+
      | 1
      | *
      |  1
      |  2
    """.stripMargin.trim,
    3
  )
  check(
    """+
      |  +
      |    1
      |    *
      |      1
      |      2
      |  *
      |    3
      |    4
      |    5
      |
        """.stripMargin.trim,
    63
  )
}

class Parser(indent: Int) {
  import fastparse._, NoWhitespace._
  def number[_: P]: P[Int] = P(CharIn("0-9").rep(1).!.map(_.toInt))
  def deeper[_: P]: P[Int] = P(" ".rep(indent + 1).!.map(_.length))
  def blockBody[_: P]: P[Seq[Int]] = "\n" ~ deeper.flatMap(i =>
    new Parser(indent = i).factor.rep(1, sep = ("\n" + " " * i)./)
  )
  def block[_: P]: P[Int] = P( CharIn("+\\-*/").! ~/ blockBody).map(eval)
  def factor[_: P]: P[Int] = P( number | block)
  def expr[_: P]: P[Int] = P(block ~ End)
  def eval(tree: (String, Seq[Int])) = tree match {
    case ("+", nums) => nums.reduceLeft(_+_)
    case ("-", nums) => nums.reduceLeft(_-_)
    case ("*", nums) => nums.reduceLeft(_*_)
    case ("/", nums) => nums.reduceLeft(_/_)
  }
}