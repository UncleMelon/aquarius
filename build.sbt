
name := "practice-based-learning"


lazy val commonSettings = Seq(
  organization := "com.wy",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.8"
)

// Set aggregate so that the command sent to root is broadcast to webservice and dal too

lazy val root = (project in file("."))
  .aggregate(algorithms, concurrency, functional, spark)

lazy val algorithms = (project in file("algorithms-learning"))
  .settings(commonSettings)

lazy val concurrency = (project in file("concurrency-learning"))
  .settings(commonSettings)

lazy val functional = (project in file("functional-features-learning"))
  .settings(commonSettings)

lazy val spark = (project in file("spark-learning"))
  .settings(commonSettings)