package myakkastream

import akka.actor.ActorSystem
import akka.stream.ActorAttributes.SupervisionStrategy
import akka.stream.stage.{GraphStage, GraphStageLogic, InHandler, OutHandler}
import akka.stream._
import akka.stream.scaladsl.RunnableGraph
import akka.stream.scaladsl.{GraphDSL, Sink, Source}

import scala.collection.immutable
import scala.util.control.NonFatal

/**
  *
  * @author matthew_wu
  * @since 2019-08-16 14:55
  */
object PipeOps {

  case class PipeShape[In, Out](
                                 in: Inlet[In],
                                 out: Outlet[Out]
                               ) extends Shape {

    override def inlets: immutable.Seq[Inlet[_]] = in :: Nil

    override def outlets: immutable.Seq[Outlet[_]] = out :: Nil

    override def deepCopy(): Shape = PipeShape(in = in.carbonCopy(), out = out.carbonCopy())
  }


  class Pipe[In, Out](f: In => Out) extends GraphStage[PipeShape[In, Out]] {
    val in = Inlet[In]("Pipe.in")
    val out = Outlet[Out]("Pipe.out")

    override def shape= PipeShape(in, out)

    override def initialAttributes: Attributes = Attributes.none

    override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = {
      new GraphStageLogic(shape) with InHandler with OutHandler {

        private def decider = inheritedAttributes.get[SupervisionStrategy].map(_.decider).getOrElse(Supervision.stoppingDecider)

        override def onPull(): Unit = pull(in)

        override def onPush(): Unit = {
          try {
            push(out, f(grab(in)))
          } catch {
            case NonFatal(ex) => decider(ex) match {
              case Supervision.Stop => failStage(ex)
              case _ => pull(in)
            }
          }
        }
        setHandlers(in, out, this)
      }
    }
  }

  def applyPipe[In,Out](f: In => Out) = GraphDSL.create() { implicit builder =>
    val pipe = builder.add(new Pipe[In, Out](f))
    FlowShape(pipe.in,pipe.out)
  }
}

object ShapeDemo1 extends App {
  import PipeOps._

  implicit val sys = ActorSystem("streamSys")
  implicit val ec = sys.dispatcher
  implicit val mat = ActorMaterializer()

  RunnableGraph.fromGraph(
    GraphDSL.create() { implicit builder =>
      import GraphDSL.Implicits._

      val source = Source(1 to 10)
      val sink = Sink.foreach(println)
      val f: Int => Int = _ * 3
      val pipeShape = builder.add(new Pipe[Int, Int](f))
      source ~> pipeShape.in
      pipeShape.out ~> sink
      ClosedShape
    }
  ).run()

  val fut = Source(1 to 10).via(applyPipe[Int,Int](_ * 2)).runForeach(println)

  scala.io.StdIn.readLine()

  sys.terminate()
}



