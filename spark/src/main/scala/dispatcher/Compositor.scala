package dispatcher

trait Compositor {
  def initialize(params: List[Map[String, String]])
  def result()
}
