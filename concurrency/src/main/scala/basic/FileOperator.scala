package basic

import java.util.concurrent.atomic.AtomicReference

sealed trait State
class Idle extends State
class Creating extends State
class Copying(val n: Int) extends State
class Deleting extends State

class Entry(val isDir: Boolean) {
  val state = new AtomicReference[State](new Idle)
}

object FileOperator {

  private def prepareForDelete(entry: Entry): Boolean = {
    val s0 = entry.state.get
    s0 match {
      case i: Idle =>
        if (entry.state.compareAndSet(s0, new Deleting)) true
        else prepareForDelete(entry)
      case c: Creating =>
        log("File currently created, cannot delete."); false
      case c: Copying =>
        log("File currently copied, cannot delete."); false
      case d: Deleting =>
        false
    }
  }

}
