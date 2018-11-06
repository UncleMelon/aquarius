package spark

import scala.io.Source
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object Test {

  def main(args: Array[String]): Unit = {
    val path = "/batch-script-df.json"
    val jobConfig = Source.fromURL(getClass.getResource(path)).getLines().mkString("\n")
    val json = parse(jobConfig)

    val map = json.values.asInstanceOf[Map[String, Any]]
    println(map.getOrElse("batch-console", None))
    val map1 = map.getOrElse("batch-console", None).asInstanceOf[Map[String, Any]]
    createCompositors(map1)

  }


  private def createCompositors(desc: Map[String, Any]) = {
    desc.get("compositor").asInstanceOf[Option[List[Map[String, Any]]]] match {
      case Some(compositors) => compositors.map(f => {
        println(f.getOrElse("name", None))
        val compositor = f.getOrElse("name", None).asInstanceOf[String]
        val params = f.getOrElse("params", None).asInstanceOf[List[Map[String, String]]]

        })
      case None =>
    }
  }
}
