import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin
import sbtdocker.DockerPlugin.autoImport._
import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._

object Packaging {

  lazy val basicSettings: Seq[Def.Setting[_]] = Seq(
    organization := "com.wy",
    version := "0.1.1",
    scalaVersion := "2.12.8",
    parallelExecution in Test := false,
    fork in Test := true,
    fork in run := true
  )

  lazy val dockerSettings = Seq(
    docker := (docker dependsOn stage.in(Universal)).value,
    buildOptions in docker := BuildOptions(
      cache = false,
      removeIntermediateContainers = BuildOptions.Remove.Always,
      pullBaseImage = BuildOptions.Pull.Always
    ),
    imageNames in docker := Seq(
      // Sets the latest tag
      sbtdocker.ImageName(s"docker-registry.teambition.net/library/${name.value}:latest"),
      // Sets a name with a tag that contains the project version
      sbtdocker.ImageName(
        namespace = Some(s"docker-registry.teambition.net/library"),
        repository = s"${name.value}",
        tag = Some(s"v${version.value}")
      )
    ),
    dockerfile in docker := {
      val targetDir = s"/opt/${name.value}"
      val mainclass = mainClass.in(Compile, packageBin).value.getOrElse(sys.error("Expected exactly one main class"))
      new Dockerfile {
        from("openjdk:8-jre-alpine")
        copy(baseDirectory(_ / "target/universal/stage").value, file(targetDir))
        entryPoint("java", "-cp", s"$targetDir/lib/*:$targetDir/conf", mainclass)
        //    cmd("tail", "-f")
      }
    }
  )

  def generateProject(name: String, path: Option[String] = None): Project = {
    Project(name, file(path.getOrElse(name))).
      enablePlugins(JvmPlugin).
      enablePlugins(JavaAppPackaging).
      enablePlugins(BuildEnvPlugin).
      enablePlugins(sbtdocker.DockerPlugin).
      settings(basicSettings).
      settings(excludeDependencies ++= Dependencies.exclude)
  }

}
