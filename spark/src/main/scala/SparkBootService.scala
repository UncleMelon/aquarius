
import task.MySparkTask
import utils.ApplicationContextUtil

/**
  *
  * @author matthew_wu
  * @since 2019-08-26 15:32
  */
object SparkBootService extends App {
  val applicationContext = ApplicationContextUtil.getApplicationContext
  val executor = applicationContext.getBean("mySparkTask", classOf[MySparkTask])
  executor.execute()
}
