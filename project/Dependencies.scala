import sbt._
import Keys._

object Dependencies {

  val logback = Seq(Library.logback)

  val hadoop = Seq(Library.`hadoop-hdfs-client`, Library.`hadoop-mapreduce-client-core`, Library.`hive-exec`)
  
  val scalaReflect = Seq(Library.`scala-reflect`)

  val core = Seq(Library.config)

  val utest = Seq(Library.utest)

  val jmh = Seq(Library.`jmh-core`, Library.`jmh-generator-annprocess`)

  val fastparse = Seq(Library.fastparse)

  val circe = Seq(Library.`circe-core`, Library.`circe-generic`, Library.`circe-parser`)

  val antlr = Seq(Library.antlr)
  
  val exclude = Seq(
    "org.slf4j" % "slf4j-log4j12",
    "org.apache.logging.log4j" % "log4j-web",
    "org.apache.logging.log4j" % "log4j-slf4j-impl",
    "org.apache.logging.log4j" % "log4j-1.2-api",
    "org.apache.logging.log4j" % "log4j-api",
    "org.apache.logging.log4j" % "log4j-core")
}

object Library {

  val slf4j = "org.slf4j" % "log4j-over-slf4j" % Version.slf4j

  val logback = "ch.qos.logback" % "logback-classic" % Version.logback
  
  val `hadoop-hdfs-client` = "org.apache.hadoop" % "hadoop-hdfs-client" % Version.hadoop

  val `hadoop-mapreduce-client-core` = "org.apache.hadoop" % "hadoop-mapreduce-client-core" % Version.hadoop

  val `hive-exec` = "org.apache.hive" % "hive-exec" % Version.hive

  val `scala-reflect` = "org.scala-lang" % "scala-reflect" % Version.scala

  val config = "com.typesafe" % "config" % Version.config

  val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % Test

  val utest =  "com.lihaoyi" %% "utest" % "0.5.3" % Test

  val guava = "com.google.guava" % "guava" % Version.guava

  val fastjson = "com.alibaba" % "fastjson" % Version.fastjson

  val `jmh-core` = "org.openjdk.jmh" % "jmh-core" % Version.jmh

  val `jmh-generator-annprocess` = "org.openjdk.jmh" % "jmh-generator-annprocess" % Version.jmh

  val antlr = "org.antlr" % "antlr4-runtime" % Version.antlr

  val fastparse = "com.lihaoyi" %% "fastparse" % Version.fastparse

  val `circe-core` = "io.circe" %% "circe-core" % Version.circe

  val `circe-generic` = "io.circe" %% "circe-generic" % Version.circe

  val `circe-parser` = "io.circe" %% "circe-parser" % Version.circe
}

object Version {

  val slf4j = "1.7.26"

  val logback = "1.2.3"

  val scalatest = "3.0.5"

  val hadoop = "3.0.0-cdh6.0.0"

  val hive = "2.1.1-cdh6.0.0"

  val scala = "2.12.8"

  val config = "1.3.3"

  val guava = "23.0"

  val fastjson = "1.2.56"

  val jmh = "1.21"

  val antlr = "4.7.1"

  val fastparse = "2.1.0"

  val circe = "0.10.0"
}
