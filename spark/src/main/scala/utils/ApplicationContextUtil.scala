package utils

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
  *
  * @author matthew_wu
  * @since 2019-08-26 16:32
  */
object ApplicationContextUtil  {

  private lazy val context = new ClassPathXmlApplicationContext("applicationContext.xml")

  def getApplicationContext: ApplicationContext = context

}
