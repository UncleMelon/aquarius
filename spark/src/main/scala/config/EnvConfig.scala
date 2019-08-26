package config

import java.io.File

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sync.PathSyncer

import scala.collection.JavaConverters._

/**
  *
  * @author matthew_wu
  * @since 2019-08-25 08:57
  */
class EnvConfig {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  var hadoopConfigs: java.util.Map[String , String] = _

  @Autowired
  var pathSyncConfig: PathSyncConfig = _

  def this(hadoopConfigs: java.util.Map[String, String]) {
    this()
    this.hadoopConfigs = hadoopConfigs
  }

  def getHadoopConfig() = {
    val config = hadoopConfigs.asScala
    val defaultFsConfig = config.get("fs.defaultFS").getOrElse(throw new RuntimeException("fs.defaultFS should be provided."))
    implicit val fs = buildHadoopFs(defaultFsConfig)
    logger.info(s"upload ${pathSyncConfig.localPathRelateToClassDir} to $defaultFsConfig${pathSyncConfig.remotePath}")
    PathSyncer.sync(new File(pathSyncConfig.localPathRelateToClassDir), new Path(pathSyncConfig.remotePath), Option(pathSyncConfig.fileMatcher))
    val sparkHadoopConfigs = config.map {case (key, value) =>  s"spark.hadoop.$key" -> value}
    sparkHadoopConfigs += ("spark.yarn.jars" -> s"$defaultFsConfig${pathSyncConfig.remotePath}/*.jar")
    sparkHadoopConfigs.toMap
  }

  private def buildHadoopFs(defaultFsConfig: String) = {
    val conf: Configuration = new Configuration()
    conf.set("fs.defaultFS", defaultFsConfig)
    FileSystem.get(conf)
  }
}