package fparse

import fastparse.Parsed._

object Calculator extends App {
  import fastparse._, SingleLineWhitespace._
  def number[_: P]: P[Int] = P( CharIn("0-9").rep(1).!.map(_.toInt) )
  def parens[_: P]: P[Int] = P( "(" ~/ addSub ~ ")" )
  def factor[_: P]: P[Int] = P( number | parens )

  def divMul[_: P]: P[Int] = P( factor ~ (CharIn("*/").! ~/ factor).rep ).map(eval)
  def addSub[_: P]: P[Int] = P( divMul ~ (CharIn("+\\-").! ~/ divMul).rep ).map(eval)
  def expr[_: P]: P[Int] = ( addSub ~ End)

  def eval(tree: (Int, Seq[(String, Int)])) = {
    val (base, ops) = tree
    ops.foldLeft(base) { case (left, (op, right)) => op match {
      case "+" => left + right
      case "-" => left - right
      case "*" => left * right
      case "/" => left / right
      }
    }
  }

  def check(str: String, num: Int) = {
    val Parsed.Success(value, _ ) = parse(str, expr(_))
    assert(value == num)
  }

  check("1+1", 2)
  check("1   +  1*    2", 3)
  check("(1+1*2)+3*4", 15)
  check("((1+1 *2)+(3*4*5))/3", 21)
}
