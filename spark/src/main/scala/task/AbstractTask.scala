package task

import java.lang.reflect.Modifier

import config.EnvConfig
import org.apache.spark.SparkConf

import scala.beans.BeanProperty

/**
  *
  * @author matthew_wu
  * @since 2019-08-26 10:42
  */
trait AbstractTask {

  @BeanProperty
  var className: String = _

  @BeanProperty
  var executorNums: String = _

  @BeanProperty
  var executorCores: String = _

  @BeanProperty
  var executorMemory: String = _

  @BeanProperty
  var driverMemory: String = _

  @BeanProperty
  var myConfName: String = _

  @BeanProperty
  var props: Map[String, String] = _

  @BeanProperty
  var envConfig: EnvConfig = _


  def buildEnv(): SparkConf = {
    val sparkConf = new SparkConf()
    sparkConf.set("spark.executor.instances", executorNums)
    sparkConf.set("spark.executor.cores", executorCores)
    sparkConf.set("spark.executor.memory", executorMemory)
    sparkConf.set("spark.driver.memory", driverMemory)
    sparkConf.set("spark.master", "yarn")
    sparkConf.set("spark.submit.deployMode", "client")
    envConfig.getHadoopConfig().foreach{ case (key, value) => sparkConf.set(key, value)}
    sparkConf
  }

  def submit(conf: SparkConf): Unit = {
    val klass: Class[_] = Class.forName(className)
    val mainMethod = klass.getMethod("main", new Array[String](0).getClass)
    if (!Modifier.isStatic(mainMethod.getModifiers)) {
      throw new IllegalStateException("The main method in the given main class must be static")
    }

    val sysProps = conf.getAll.toMap
    sysProps.foreach { case (k, v) =>
      sys.props(k) = v
    }

    mainMethod.invoke(null, Array.empty[String])
  }

  def execute() = {
    submit(buildEnv())
  }

}
