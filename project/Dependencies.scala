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

  val prometheus = Seq(
    Library.`prometheus-simpleclient-hotspot`,
    Library.`prometheus-simpleclient-httpserver`,
    Library.`prometheus-simpleclient`,
    Library.`prometheus-simpleclient-pushgateway`)

  val influxdb = Seq(Library.`influxdb-java`)

  val flink = Seq(Library.`flink-clients`, Library.`flink-streaming-java`)

  val rxscala = Seq(Library.rxscala)

  val akka = Seq(
    Library.`akka-actor`,
    Library.`akka-stream`,
    Library.`akka-stream-kafka`,
    Library.`kafka-clients`,
    Library.`akka-stream-alpakka-slick`,
    Library.`akka-http`)

  val mongo = Seq(Library.`mongodb-driver`)

  val slick = Seq(Library.slick, Library.`slick-hikaricp`, Library.postgresql)

  val exclude = Seq(
    "org.slf4j"                % "slf4j-log4j12",
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

  val utest = "com.lihaoyi" %% "utest" % "0.5.3" % Test

  val guava = "com.google.guava" % "guava" % Version.guava

  val fastjson = "com.alibaba" % "fastjson" % Version.fastjson

  val `jmh-core` = "org.openjdk.jmh" % "jmh-core" % Version.jmh

  val `jmh-generator-annprocess` = "org.openjdk.jmh" % "jmh-generator-annprocess" % Version.jmh

  val antlr = "org.antlr" % "antlr4-runtime" % Version.antlr

  val fastparse = "com.lihaoyi" %% "fastparse" % Version.fastparse

  val `circe-core` = "io.circe" %% "circe-core" % Version.circe

  val `circe-generic` = "io.circe" %% "circe-generic" % Version.circe

  val `circe-parser` = "io.circe" %% "circe-parser" % Version.circe

  val `prometheus-simpleclient` = "io.prometheus" % "simpleclient" % Version.prometheus

  val `prometheus-simpleclient-hotspot` = "io.prometheus" % "simpleclient_hotspot" % Version.prometheus

  val `prometheus-simpleclient-httpserver` = "io.prometheus" % "simpleclient_httpserver" % Version.prometheus

  val `prometheus-simpleclient-pushgateway` = "io.prometheus" % "simpleclient_pushgateway" % Version.prometheus

  val `influxdb-java` = "org.influxdb" % "influxdb-java" % Version.`influxdb-java`

  val `flink-clients` = "org.apache.flink" %% "flink-clients" % Version.flink

  val `flink-streaming-java` = "org.apache.flink" %% "flink-streaming-java" % Version.flink

  val rxscala = "io.reactivex" %% "rxscala" % Version.rxscala

  val `akka-http` = "com.typesafe.akka" %% "akka-http" % "10.1.8"

  val `akka-actor` = "com.typesafe.akka" %% "akka-actor" % Version.akka

  val `akka-stream` = "com.typesafe.akka" %% "akka-stream" % Version.akka

  val `akka-stream-kafka` = "com.typesafe.akka" %% "akka-stream-kafka" % "1.0.4"

  val `kafka-clients` = "org.apache.kafka" % "kafka-clients" % Version.kafka

  val `slick-hikaricp` = "com.typesafe.slick" %% "slick-hikaricp" % Version.slick

  val slick = "com.typesafe.slick" %% "slick" % Version.slick

  val `akka-stream-alpakka-slick` = "com.lightbend.akka" %% "akka-stream-alpakka-slick" % "1.1.0"

  val postgresql = "org.postgresql" % "postgresql" % "9.4.1212"

  val `mongodb-driver` = "org.mongodb" % "mongodb-driver" % "3.10.2"
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

  val antlr = "4.7.2"

  val fastparse = "2.1.0"

  val circe = "0.10.0"

  val prometheus = "0.6.0"

  val `influxdb-java` = "2.15"

  val flink = "1.8.0"

  val rxscala = "0.26.5"

  val akka = "2.5.23"

  val kafka = "1.1.1"

  val slick = "3.3.2"
}
