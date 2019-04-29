package fparse

import fastparse.Parsed._
import fastparse.internal.Logger
import utest._

object FPBasicTest extends TestSuite {

  override def tests  = Tests {
    'basic {
      'simple {
        import fastparse._
        def parseA[_: P] = P("a")

        val Parsed.Success(value, successIndex) = parse("a", parseA(_))
        assert(value == (), successIndex == 1)

        val f @ Parsed.Failure(label, index, extra) = parse("b", parseA(_))
        assert(
          label == "",
          index == 0,
          f.msg == """Position 1:1, found "b""""
        )
      }

      'failures {
        import fastparse._, NoWhitespace._
        def parseEither[_: P] = P("a" | "b")
        def parseA[_: P] = P(parseEither.? ~ "c")
        val f @ Parsed.Failure(failureString, index, extra) = parse("d", parseA(_))

        assert(
          failureString == "",
          index == 0,
          f.msg == """Position 1:1, found "d""""
        )

        val trace = f.trace()

        // `.msg` records the last parser that failed, which is "c", and
        // `.longMsg` also shows the parsing stack at point of failure
        assert(
          trace.label == "\"c\"",
          trace.index == 0,
          trace.msg == """Expected "c":1:1, found "d"""",
          trace.longMsg == """Expected parseA:1:1 / "c":1:1, found "d""""
        )

        // aggregateMsg and longAggregateMsg record all parsers
        // failing at the position, "a" | "b" | "c",

        assert(
          trace.aggregateMsg == """Expected (parseEither | "c"):1:1, found "d"""",
          trace.longAggregateMsg == """Expected parseA:1:1 / (parseEither | "c"):1:1, found "d""""
        )
      }

      'sequence {
        import fastparse._, NoWhitespace._
        def ab[_: P] = P("a" ~ "b")
        val Parsed.Success(_, 2) = parse("ab", ab(_))
        val Parsed.Failure(_, 1, _) = parse("aa", ab(_))
      }

      'repeat {
        import fastparse._, NoWhitespace._
        def ab[_: P] = P("a".rep ~ "b")
        val Parsed.Success(_, 8) = parse("aaaaaaab", ab(_))
        val Parsed.Success(_, 4) = parse("aaaba", ab(_))

        def abc[_: P] = P("a".rep(sep="b") ~ "c")
        val Parsed.Success(_, 8) = parse("abababac", abc(_))
        val Parsed.Failure(_, 3, _) = parse("abaabac", abc(_))

        def ab4[_: P] = P("a".rep(min = 2, max = 4, sep = "b"))
        val Parsed.Success(_, 7) = parse("ababababababa", ab4(_))

        def ab2exactly[_: P] = P("ab".rep(exactly = 2))
        val Parsed.Success(_, 4) = parse("abab", ab2exactly(_))

        def ab4c[_: P] = P("a".rep(min = 2, max = 4, sep = "b") ~ "c")
        val Parsed.Failure(_, 1, _) = parse("ac", ab4c(_))
        val Parsed.Success(_, 4) = parse("abac", ab4c(_))
        val Parsed.Success(_, 8) = parse("abababac", ab4c(_))
        val Parsed.Failure(_, 7, _) = parse("ababababac", ab4c(_))
      }

      'optional {
        import fastparse._, NoWhitespace._
        def option[_: P] = P("c".? ~ "a".rep(sep="b").! ~ End)
        val Parsed.Success("aba", 3) = parse("aba", option(_))
      }

      'either {
        import fastparse._, NoWhitespace._
        def either[_: P] = P("a".rep ~ ("b" | "c" | "d") ~ End)
        val Parsed.Success(_, 6) = parse("aaaaab", either(_))
        val f @ Parsed.Failure(_, 5, _) = parse("aaaaae", either(_))
        val trace = f.trace().longAggregateMsg
        assert(
          f.toString == """Parsed.Failure(Position 1:6, found "e")""",
          trace == """Expected either:1:1 / ("a" | "b" | "c" | "d"):1:6, found "e""""
        )
      }

      'end {
        import fastparse._, NoWhitespace._
        def noEnd[_: P] = P("a".rep ~ "b")
        def withEnd[_: P] = P("a".rep ~ "b" ~ End)
        val Parsed.Success(_, 4) = parse("aaaba", noEnd(_))
        val Parsed.Failure(_, 4, _) = parse("aaaba", withEnd(_))
      }

      'start {
        import fastparse._, NoWhitespace._
        def ab[_: P] = P( (("a" | Start) ~ "b").rep ~ End).!

        val Parsed.Success("abab", 4) = parse("abab", ab(_))
        val Parsed.Success("babab", 5) = parse("babab", ab(_))

        val Parsed.Failure(_, 2, _) = parse("abb", ab(_))
      }

      'passFail {
        import fastparse._, NoWhitespace._
        val Parsed.Success((), 0) = parse("asdad", Pass(_))
        val Parsed.Failure(_, 0, _) = parse("asdad", Fail(_))
      }

      'index {
        import fastparse._, NoWhitespace._
        def finder[_: P] = P("hay".rep ~ Index ~ "needle" ~ "hay".rep)
        val Parsed.Success(9, _) = parse("hayhayhayneedlehay", finder(_))
      }

      'capture {
        import fastparse._, NoWhitespace._
        def capture[_: P] = P("a".rep.! ~ "b" ~ End)
        val Parsed.Success("aaa", 4) = parse("aaab", capture(_))
        def capture2[_: P] = P("a".rep.! ~ "b".! ~ End)
        val Parsed.Success(("aaa", "b"), 4) = parse("aaab", capture2(_))
        def capture3[_: P] = P("a".rep.! ~ "b".! ~ "c".! ~ End)
        val Parsed.Success(("aaa", "b", "c"), 5) = parse("aaabc", capture3(_))
        def captureRep[_: P] = P("a".!.rep ~ "b" ~ End)
        val Parsed.Success(Seq("a", "a", "a"), 4) = parse("aaab", captureRep(_))
        def captureOpt[_: P] = P("a".rep ~ "b".!.? ~ End)
        val Parsed.Success(Some("b"), 4) = parse("aaab", captureOpt(_))
      }

      'anyChar {
        import fastparse._, NoWhitespace._
        def ab[_: P] = P("'" ~ AnyChar.! ~ "'")
        val Parsed.Success("-", 3) = parse("'-'", ab(_))
        val Parsed.Failure(stack, 2, _) = parse("'-='", ab(_))
      }

      'lookahead {
        import fastparse._, NoWhitespace._
        def keywords[_: P] = P(("hello" ~ &(" ")).!.rep)
        val Parsed.Success(Seq("hello"), _) = parse("hello ", keywords(_))
        val Parsed.Success(Seq(), _) = parse("helloX", keywords(_))
      }

      'neglookahead {
        import fastparse._, NoWhitespace._
        def keywords[_: P] = P("hello" ~ !" " ~ AnyChar ~ "world").!
        val Parsed.Success("hello-world", _) = parse("hello-world", keywords(_))
        val Parsed.Success("hello_world", _) = parse("hello_world", keywords(_))
        val Parsed.Failure(_, 5, _) = parse("hello world", keywords(_))
      }

      'map {
        import fastparse._, NoWhitespace._
        def binary[_: P] = P( ("0" | "1").rep.!)
        def binaryNum[_: P] = P(binary.map(Integer.parseInt(_, 2)))
        val Parsed.Success("1100", _) = parse("1100", binary(_))
        val Parsed.Success(12, _) = parse("1100", binaryNum(_))
      }

      'flatMap {
        import fastparse._, NoWhitespace._
        def leftTag[_: P] = P("<" ~ (!">" ~ AnyChar).rep(1).! ~ ">")
        def rightTag[_: P](s: String) = P("</" ~ s.! ~ ">")
        def xml[_: P] = P(leftTag.flatMap(rightTag))
        val Parsed.Success("a", _) = parse("<a></a>", xml(_))
        val Parsed.Success("abcde", _) = parse("<abcde></abcde>", xml(_))
        val failure = parse("<abcde></edcba>", xml(_)).asInstanceOf[Parsed.Failure]
        assert(
          failure.trace().longAggregateMsg == """Expected xml:1:1 / rightTag:1:8 / "abcde":1:10, found "edcba>""""
        )
      }

      'filter {
        import fastparse._, NoWhitespace._
        def digits[_: P] = P(CharPred(c => '0' <= c && c <= '9').rep(1).!).map(_.toInt)
        def even[_: P] = P(digits.filter(_ % 2 == 0))
        val Parsed.Success(12, _) = parse("12", even(_))
        val failure = parse("123", even(_)).asInstanceOf[Parsed.Failure]
      }

      'opaque {
        import fastparse._, NoWhitespace._
        def digit[_: P] = CharIn("0-9")
        def letter[_: P] = CharIn("A-Z")
        def twice[T, _: P](p: => P[T]) =  p ~ p
        def errorMessage[T](p: P[_] => P[T], str: String) =
          parse(str, p).asInstanceOf[Parsed.Failure].trace().longAggregateMsg
        def numberPlate[_: P] = P(twice(digit) ~ "-" ~ twice(letter) ~ "-" ~ twice(digit))
        val err1 = errorMessage(numberPlate(_), "11-A1-22")
        assert(err1 == """Expected numberPlate:1:1 / [A-Z]:1:5, found "1-22"""")
        def opaqueNumberPlate[_: P] = numberPlate.opaque("<number-plate>")
        val err2 = errorMessage(opaqueNumberPlate(_), "11-A1-22")
        assert(err2 == """Expected <number-plate>:1:1, found "11-A1-22"""")
      }

      'log {
        import fastparse._, NoWhitespace._
        val logged = collection.mutable.Buffer.empty[String]
        implicit val logger = Logger(logged.append(_))
        def DeepFailure[_: P] = P("C").log
        def Foo[_: P] = P( (DeepFailure | "A") ~ "B".!).log
        parse("AB", Foo(_))
        val allLogged = logged.mkString("\n")
        val expected =
          """+Foo:1:1, cut
            |  +DeepFailure:1:1
            |  -DeepFailure:1:1:Failure(DeepFailure:1:1 / "C":1:1 ..."AB")
            |-Foo:1:1:Success(1:3, cut)
            |
          """.stripMargin.trim
        assert(allLogged == expected)
      }
    }
  }
}
