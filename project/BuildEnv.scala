import BuildEnv.BuildEnv
import sbt.{AutoPlugin, Def}
import sbt._
import Keys._

object BuildEnvPlugin extends AutoPlugin {

  object autoImport {
    val buildEnv = settingKey[BuildEnv]("build env")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    buildEnv := {
      sys.props.get("env")
        .orElse(sys.env.get("BUILD_ENV"))
        .flatMap {
          case "prod" => Some(BuildEnv.Production)
          case "com.teambition.hdfs.stage" => Some(BuildEnv.Stage)
          case "test" => Some(BuildEnv.Test)
          case "dev" => Some(BuildEnv.Development)
          case unknown => None
        }
        .getOrElse(BuildEnv.Development)
    }
  )
}

object BuildEnv extends Enumeration {
  type BuildEnv = Value
  val Production = Value("1")
  val Stage = Value("2")
  val Test = Value("3")
  val Development = Value("4")
  def checkExists(env: String) = this.values.exists(_.toString == env)
  def showAll = this.values.foreach(println)
}