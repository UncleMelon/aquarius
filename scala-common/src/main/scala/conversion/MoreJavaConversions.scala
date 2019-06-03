package conversion

object MoreJavaConversions {
  import scala.collection.JavaConverters._
  implicit class RichListLong(val list: Option[List[Long]]) extends AnyVal {
    def asJava : java.util.List[java.lang.Long] = list.map(_.map(long2Long).asJava).orNull
  }
  implicit class RichArrayLong(val array: Array[Long]) extends AnyVal {
    def asJava : Array[java.lang.Long] = array.map(long2Long)
  }
  implicit class RichOptionBoolean(val bool: Option[Boolean]) extends AnyVal {
    def asJava : java.lang.Boolean = bool.map(boolean2Boolean).orNull
  }
}
