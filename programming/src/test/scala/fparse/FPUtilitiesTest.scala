package fparse

import fastparse.Parsed._
import utest._


object FPUtilitiesTest extends TestSuite {

  override def tests: Tests = Tests {
    'utilities {
      'charPred {
        import fastparse._, NoWhitespace._
        def cp[_: P] = P( CharPred(_.isUpper).rep.! ~ "." ~ End )
        val Parsed.Success("ABC", _) = parse("ABC.", cp(_))
        val Parsed.Failure(_, 2, _) = parse("ABc.", cp(_))
      }
      'charIn {
        import fastparse._, NoWhitespace._
        def ci[_: P] = P(CharIn("abc", "xyz").rep.! ~ End)
        val Parsed.Success("aaabbccxyz", _) = parse("aaabbccxyz", ci(_))
        val Parsed.Failure(_ ,7 ,_) = parse("aaabbccdxyz", ci(_))
        def digits[_: P] = P(CharIn("0-9").rep.!)
        val Parsed.Success("12345", _) = parse("12345", digits(_))
        val Parsed.Success("123", _) = parse("123abcde45", digits(_))
      }
      'charsWhile {
        import fastparse._, NoWhitespace._
        def cw[_: P] = P(CharsWhile(_ != ' ').!)
        val Parsed.Success("12345", _) = parse("12345", cw(_))
        val Parsed.Success("123", _) = parse("123 45", cw(_))
      }
      'charsWhileIn {
       import fastparse._, NoWhitespace._
        def cw[_: P] = P(CharsWhileIn("123456789").!)
        val Parsed.Success("12345", _) = parse("12345", cw(_))
        val Parsed.Success("123", _) = parse("123 45", cw(_))
      }
      'stringIn {
        import fastparse._, NoWhitespace._
        def si[_: P] = P(StringIn("cow", "cattle").!.rep(1))
        val Parsed.Success(Seq("cow", "cattle"), _) = parse("cowcattle", si(_))
        val Parsed.Success(Seq("cow"), _) = parse("cowmoo", si(_))
        val Parsed.Failure(_, _, _) = parse("co", si(_))
      }
    }
    /**
     * @Description FastParse parsers have to buffer the input in-memory
      *             in case the parser needs to backtrack, but adding Cuts tells the parser it
      *             can safely drop earlier parts of the input it can no longer backtrack to.
     **/
    'Cuts {
      'cuts {
        import fastparse._, NoWhitespace._
        def alpha[_: P] = P(CharIn("a-z"))
        def nocut[_: P] = P("val " ~/ alpha.rep(1).! | "def " ~/ alpha.rep(1).!)
        val Parsed.Success("abcd", _) = parse("val abcd", nocut(_))
        val failure = parse("val 1234", nocut(_)).asInstanceOf[Parsed.Failure]
        val trace = failure.trace().longAggregateMsg
        assert(
          failure.index == 4,
          trace == """Expected nocut:1:1 / alpha:1:5 / [a-z]:1:5, found "1234""""
        )
      }
      'repCuts {
        import fastparse._, NoWhitespace._
        def alpha[_: P] = P( CharIn("a-z") )
        def stmt[_: P] = P( "val " ~/ alpha.rep(1).! ~ ";" ~ " ".rep )
        def stmts[_: P] = P( stmt.rep(1) ~ End )

        val Parsed.Success(Seq("abcd"), _) = parse("val abcd;", stmts(_))
        val Parsed.Success(Seq("abcd", "efg"), _) = parse("val abcd; val efg;", stmts(_))

        val failure = parse("val abcd; val ", stmts(_)).asInstanceOf[Parsed.Failure]
        val trace = failure.trace().longAggregateMsg
        assert(
          failure.index == 14,
          trace == """Expected stmts:1:1 / stmt:1:11 / alpha:1:15 / [a-z]:1:15, found """""
        )
      }
      'repCuts2 {
        import fastparse._, NoWhitespace._
        def digits[_: P] = P( CharIn("0-9").rep(1) )
        def tuple[_: P] = P( "(" ~ digits.!.rep(sep=","./) ~ ")" )

        val Parsed.Success(Seq("1", "23"), _) = parse("(1,23)", tuple(_))

        val failure = parse("(1,)", tuple(_)).asInstanceOf[Parsed.Failure]
        val index = failure.index
        val trace = failure.trace().longAggregateMsg
        assert(
          index == 3,
          trace == """Expected tuple:1:1 / digits:1:4 / [0-9]:1:4, found ")""""
        )
      }
      'isolatingCuts {
        import fastparse._, NoWhitespace._
        def digit[_: P] = P( CharIn("0-9") )
        def time1[_: P] = P( ("1".? ~ digit) ~ ":" ~/ digit ~ digit ~ ("am" | "pm") )
        def time2[_: P] = P( (("1" | "2").? ~ digit) ~ ":" ~/ digit ~ digit )
        val Parsed.Success((), _) = parse("12:30pm", time1(_))
        val Parsed.Success((), _) = parse("17:45", time2(_))
        def time[_: P] = P( NoCut(time1) | time2 )
        val Parsed.Success((), _) = parse("12:30pm", time(_))
        val Parsed.Success((), _) = parse("17:45", time(_))
      }
    }
    'streaming {
      import fastparse._,NoWhitespace._
      def p[_: P] = P( "ab" ~/ "cd".rep().! ~ "ef" | "z" )
      val Parsed.Success(res, i) = parse(Iterator("ab", "cd", "cd", "cd", "ef"), p(_))
      assert(res == "cdcdcd")
    }
  }
}
