package leetcode

import scala.collection.mutable

object Task1  extends App {

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val map = scala.collection.mutable.Map.empty[Int, Int]
    nums.zipWithIndex.foreach { case (int, index) =>
      map.keys.foreach { num =>
        if (num == int) {
          return Array(map.get(int).get, index)
        }
      }
      map.put(target - int, index)
    }
    Array.emptyIntArray
  }

  def twoSum1(xs: Array[Int], target: Int): Option[(Int, Int)] = {
    val indicesByValue = xs.zipWithIndex.toMap
    val matchIndices = xs map {x => indicesByValue get (target - x)}
    val allSolutions = (xs.indices zip matchIndices) filter { case (_, m) => m.isDefined }
    allSolutions collectFirst { case (i, Some(j)) => (i, j)}
  }

  def twoSum2(xs: Array[Int], target: Int): Array[Int] = {
    val indicesByValue = mutable.Map[Int, Int]()
    for (assoc @ (x, i) <- xs.zipWithIndex)
      indicesByValue get (target - x) match {
        case Some(j) => return Array(j, i)
        case None => indicesByValue += assoc
      }
    throw new NoSuchElementException()
  }
}
