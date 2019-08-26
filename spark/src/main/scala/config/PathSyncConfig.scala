package config

import org.apache.spark.internal.Logging
import scala.beans.BeanProperty

/**
  *
  * @author matthew_wu
  * @since 2019-08-23 18:10
  */
class PathSyncConfig extends Logging{

  @BeanProperty
  var localPathRelateToClassDir: String = _

  @BeanProperty
  var remotePath: String = _

  @BeanProperty
  var fileMatcher: RegexFileMatcher = _

  log.debug("constructor test")

}