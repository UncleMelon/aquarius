
import org.springframework.context.support.ClassPathXmlApplicationContext
import task.MySparkTask

/**
  *
  * @author matthew_wu
  * @since 2019-08-26 15:32
  */
object SparkBootService extends App {
  val ap = new ClassPathXmlApplicationContext("applicationContext.xml")
  val executor = ap.getBean("mySparkTask", classOf[MySparkTask])
  executor.execute()
}
