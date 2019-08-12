name := "aquarius"

mainClass in Compile := Some("exercises.Fibonacci")

// Set aggregate so that the command sent to root is broadcast to webservice and dal too
lazy val root = (project in file("."))
  .aggregate(algorithms, concurrency, functional, spark)

lazy val algorithms = Packaging.generateProject("algorithms")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect
  ).flatten
  )
  .settings(Packaging.dockerSettings)

lazy val concurrency = Packaging.generateProject("concurrency")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.utest,
    Dependencies.rxscala,
    Dependencies.akka,
    Dependencies.slick,
    Dependencies.mongo,
    Dependencies.circe
  ).flatten
  )

lazy val functional = Packaging.generateProject("functional")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.utest
  ).flatten
  )

lazy val spark = Packaging.generateProject("spark")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.utest
  ).flatten
  )


lazy val flink = Packaging.generateProject("flink")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.flink,
    Dependencies.utest
  ).flatten
  )

lazy val `scala-common` = Packaging.generateProject("scala-common")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.utest
  ).flatten
  )

lazy val programming = Packaging.generateProject("programming")
  .settings(libraryDependencies ++= Seq(
    Dependencies.logback,
    Dependencies.scalaReflect,
    Dependencies.jmh,
    Dependencies.fastparse,
    Dependencies.circe,
    Dependencies.antlr,
    Dependencies.prometheus,
    Dependencies.influxdb,
    Dependencies.utest
  ).flatten
  )
  .enablePlugins(JmhPlugin)

