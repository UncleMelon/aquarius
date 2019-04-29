package fpinscala.charpter4

import scala.{Either => _, Option => _, Some => _, _}


sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(a) => a
  }

  def flatMap[B](f: A => Option[B]): Option[B] =
    map(f) getOrElse None

  def flatMap_1[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  // Some(_) 推导出  x => Some(x)
  def orElse[B >: A](ob: => Option[B]): Option[B] =
    this map (Some(_)) getOrElse ob


  def orElse_1[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) if f(x) => this
    case _ => None
  }

  def filter_1(f: A => Boolean): Option[A] =
    flatMap(a => if (f(a)) Some(a) else None)

}

case object None extends Option[Nothing]
case class Some[+A](get: A) extends Option[A]

case class Employee(name: String, department: String)

object Employee {

  def lookupByName(name: String): Option[Employee] = ???

  val joeDepartment: Option[String] = lookupByName("Joe").map(_.department)
}

object Option {


  // _ map(f) 代表 x => x map(f)
  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map(f)

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None else Some(xs.sum/xs.length)

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap(m => mean(xs.map(x => math.pow(x-m, 2))))

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    a.flatMap( x => b.map(y => f(x,y)))
  }

  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    a match {
      case Nil => Some(Nil)
      case h :: t => h flatMap(hh => sequence(t) map (x => hh :: x ))
    }
  }

  def sequence_1[A](a: List[Option[A]]): Option[List[A]] = {
    a.foldRight[Option[List[A]]](Some(Nil))((a, b) => map2(a, b)(_ :: _))
  }

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a  match {
      case Nil => Some(Nil)
      case h :: t =>  map2(f(h), traverse(t)(f))(_ :: _)
    }
  }

  def traverse_1[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a.foldRight[Option[List[B]]](Some(Nil))((h,t) => map2(f(h), t)(_::_))
  }

  def sequenceViatraverse[A, B](a: List[Option[A]]): Option[List[A]] = {
    traverse(a)(x => x)
  }

}
