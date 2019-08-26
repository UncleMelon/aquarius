/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.deploy

import java.io.File
import java.lang.reflect.{InvocationTargetException, UndeclaredThrowableException}
import java.net.URL

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark._
import org.apache.spark.internal.Logging
import org.apache.spark.internal.config._
import org.apache.spark.util._
import sync.PathSyncer.sync

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

/**
 * Main gateway of launching a Spark application.
 *
 * This program handles setting up the classpath with relevant Spark dependencies and provides
 * a layer over the different cluster managers and deploy modes that Spark supports.
 */
class MySparkSubmit extends Logging {

  import DependencyUtils._
  import MySparkSubmit._

  /**
   * Submit the application using the provided parameters.
   *
   * This runs in two steps. First, we prepare the launch environment by setting up
   * the appropriate classpath, system properties, and application arguments for
   * running the child main class based on the cluster manager and the deploy mode.
   * Second, we use this launch environment to invoke the main method of the child
   * main class.
   */
   def submit(): Unit = {
    val childArgs = ArrayBuffer.empty
    val childClasspath = new ArrayBuffer[String]()
//    childClasspath.append("file:/Users/matthew_wu/Documents/IdeaProjects/aquarius/spark/target/universal/stage/lib/com.wy.spark-0.1.1.jar")
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.executor.memory", "512m")
    sparkConf.setIfMissing("spark.driver.memory", "512m")
    sparkConf.setIfMissing("spark.master", "yarn")
    sparkConf.setIfMissing("spark.app.name", "org.apache.spark.examples.SparkPi")
    sparkConf.setIfMissing("spark.executor.cores", "1")
    sparkConf.setIfMissing("spark.submit.deployMode", "client")
    sparkConf.setIfMissing("spark.executor.instances", "1")

     val conf: Configuration = new Configuration()
     conf.set("fs.defaultFS", "hdfs://192.168.0.22:8020")
     conf.setBoolean("fs.hdfs.impl.disable.cache", true)
     implicit val fs = FileSystem.get(conf)
     sync(new File("/Users/matthew_wu/Documents/IdeaProjects/aquarius/spark/target/universal/stage/lib"), new Path("/user/spark/matthew/jars"))

    sparkConf.setIfMissing("spark.hadoop.yarn.resourcemanager.hostname", "192.168.0.22")
    sparkConf.setIfMissing("spark.hadoop.yarn.resourcemanager.address", "192.168.0.22:8032")
    sparkConf.setIfMissing("spark.hadoop.fs.defaultFS", "hdfs://192.168.0.22:8020/")
    sparkConf.setIfMissing("spark.hadoop.mapreduce.framework.name", "yarn")

     val hdfs_conf = SparkHadoopUtil.get.newConfiguration(sparkConf)
     val hdfs = FileSystem.get(hdfs_conf)

    sparkConf.setIfMissing("spark.yarn.jars", "hdfs://192.168.0.22:8020/user/spark/matthew/jars/*.jar")
    val childMainClass = "driver.SparkPi"
    runMain(childArgs, childClasspath, sparkConf, childMainClass, true)

  }


  /**
   * Run the main method of the child class using the provided launch environment.
   *
   * Note that this main class will not be the one provided by the user if we're
   * running cluster deploy mode or python applications.
   */
  private def runMain(
      childArgs: Seq[String],
      childClasspath: Seq[String],
      sparkConf: SparkConf,
      childMainClass: String,
      verbose: Boolean): Unit = {
    if (verbose) {
      logInfo(s"Main class:\n$childMainClass")
      logInfo(s"Arguments:\n${childArgs.mkString("\n")}")
      // sysProps may contain sensitive information, so redact before printing
      logInfo(s"Spark config:\n${Utils.redact(sparkConf.getAll.toMap).mkString("\n")}")
      logInfo(s"Classpath elements:\n${childClasspath.mkString("\n")}")
      logInfo("\n")
    }

    val loader =
      if (sparkConf.get(DRIVER_USER_CLASS_PATH_FIRST)) {
        new ChildFirstURLClassLoader(new Array[URL](0),
          Thread.currentThread.getContextClassLoader)
      } else {
        new MutableURLClassLoader(new Array[URL](0),
          Thread.currentThread.getContextClassLoader)
      }
    Thread.currentThread.setContextClassLoader(loader)

    for (jar <- childClasspath) {
      addJarToClasspath(jar, loader)
    }

    var mainClass: Class[_] = null

    try {
      mainClass = Utils.classForName(childMainClass)
    } catch {
      case e: ClassNotFoundException =>
        logWarning(s"Failed to load $childMainClass.", e)
        if (childMainClass.contains("thriftserver")) {
          logInfo(s"Failed to load main class $childMainClass.")
          logInfo("You need to build Spark with -Phive and -Phive-thriftserver.")
        }
        throw new SparkUserAppException(CLASS_NOT_FOUND_EXIT_STATUS)
      case e: NoClassDefFoundError =>
        logWarning(s"Failed to load $childMainClass: ${e.getMessage()}")
        if (e.getMessage.contains("org/apache/hadoop/hive")) {
          logInfo(s"Failed to load hive class.")
          logInfo("You need to build Spark with -Phive and -Phive-thriftserver.")
        }
        throw new SparkUserAppException(CLASS_NOT_FOUND_EXIT_STATUS)
    }

    val app: SparkApplication = if (classOf[SparkApplication].isAssignableFrom(mainClass)) {
      mainClass.newInstance().asInstanceOf[SparkApplication]
    } else {
      // SPARK-4170
      if (classOf[scala.App].isAssignableFrom(mainClass)) {
        logWarning("Subclasses of scala.App may not work correctly. Use a main() method instead.")
      }
      new JavaMainApplication(mainClass)
    }

    @tailrec
    def findCause(t: Throwable): Throwable = t match {
      case e: UndeclaredThrowableException =>
        if (e.getCause() != null) findCause(e.getCause()) else e
      case e: InvocationTargetException =>
        if (e.getCause() != null) findCause(e.getCause()) else e
      case e: Throwable =>
        e
    }

    try {
      app.start(childArgs.toArray, sparkConf)
    } catch {
      case t: Throwable =>
        throw findCause(t)
    }
  }
}



object MySparkSubmit extends CommandLineUtils with Logging {

  private val CLASS_NOT_FOUND_EXIT_STATUS = 101

  override def main(args: Array[String]): Unit = {
    val submit = new MySparkSubmit()
    submit.submit()
  }
}