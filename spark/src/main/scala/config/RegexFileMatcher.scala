package config

import java.io.{File, FileFilter}
import java.util.regex.Pattern

import scala.beans.BeanProperty

/**
  *
  * @author matthew_wu
  * @since 2019-08-23 19:35
  */
class RegexFileMatcher extends FileFilter{

  @BeanProperty
  var namePattern: String = _

  override def accept(pathname: File): Boolean = Pattern.matches(namePattern, pathname.getName)
}
