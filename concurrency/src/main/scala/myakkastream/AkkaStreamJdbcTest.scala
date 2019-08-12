package myakkastream

import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.slick.scaladsl._
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import slick.jdbc.GetResult

import scala.concurrent.Future

/**
 *
 * @author matthew_wu
 * @since 2019-07-19 14:18
 */
object AkkaStreamJdbcTest extends App {

  implicit val system  = ActorSystem()
  implicit val mat     = ActorMaterializer()
  implicit val ec      = system.dispatcher
  implicit val session = SlickSession.forConfig("slick-postgres")
  system.registerOnTermination(session.close())
  case class User(id: Int, name: String)
  val users = (1 to 100000).map(i => User(i, s"Name$i"))

  implicit val getUserResult = GetResult(r => User(r.nextInt, r.nextString))
  import session.profile.api._

  val done: Future[Done] =
    Source(users).runWith(
      // add an optional first argument to specify the parallelism factor (Int)
      Slick.sink(user => sqlu"insert into test_user values (${user.id}, ${user.name})"))

  done.foreach(_ => println("done"))

  val tt = Source(20 to 30)
    .via(Slick.flowWithPassThrough({ i =>
      sql"select * from test_user where id = $i".as[User].head.map(u => User(u.id, u.name + "ntt"))
    }))
    .log("nr-of-updated-rows")
    .runWith(Slick.sink({ u =>
      sqlu"update test_user set name = ${u.name} where id = ${u.id}"
    }))

}

object AkkaStreamJdbcTest1 extends App {

  implicit val system  = ActorSystem()
  implicit val mat     = ActorMaterializer()
  implicit val ec      = system.dispatcher
  implicit val session = SlickSession.forConfig("slick-postgres")
  system.registerOnTermination(session.close())
  case class User(id: Int, name: String)
  val users = (1 to 100000).map(i => User(i, s"Name$i"))

  implicit val getUserResult = GetResult(r => User(r.nextInt, r.nextString))
  import session.profile.api._

  Slick.source(sql"select id, name from test_user".as[User]).log("user").runWith(Sink.foreach(println))

  Slick
    .source(sql"select id, name from test_user".as[User])
    .map(u => User(u.id, u.name + "=============="))
    .log("user")
    .runWith(Sink.foreach(println))
}

object AkkaStreamJdbcTest2 extends App {

  implicit val system  = ActorSystem()
  implicit val mat     = ActorMaterializer()
  implicit val ec      = system.dispatcher
  implicit val session = SlickSession.forConfig("slick-postgres")
  system.registerOnTermination(session.close())
  case class User(id: Int, name: String)
  val users = (1 to 100000).map(i => User(i, s"Name$i"))

  implicit val getUserResult = GetResult(r => User(r.nextInt, r.nextString))
  import session.profile.api._

  def createBatchUpsert(values: Iterable[User]): SimpleDBIO[Array[Int]] = {
    val SQL = """INSERT INTO test_user (id, name) VALUES (?, ?);"""
    SimpleDBIO { context =>
      val statement = context.session.conn.prepareStatement(SQL)
      values.map { row =>
        statement.setInt(1, row.id)
        statement.setString(2, row.name)
        statement.addBatch()
      }
      statement.executeBatch()
    }
  }

  session.db.run(DBIO.seq(createBatchUpsert(users)).transactionally)
}
