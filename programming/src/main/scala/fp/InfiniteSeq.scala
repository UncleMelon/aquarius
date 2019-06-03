package fp

object InfiniteSeq extends App {

  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  println(from(0).map(_ * 4).take(3).toList)

}
