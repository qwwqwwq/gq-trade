package utils

import scala.collection._
import mutable.ListBuffer

class FixedLengthList[A](max: Int) extends Traversable[A] {

  val list: ListBuffer[A] = ListBuffer()

  def append(elem: A) {
    if (list.size == max) {
      list.trimStart(1)
    }
    list.append(elem)
  }

  def foreach[U](f: A => U): Unit = list.foreach(f)

  def map[U](f: A => U): Seq[U] = list.map(f)
}
