package fpinscala.charpter3


sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def size[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(l, r) => 1 + size(l) + size(r)
    }
  }

  def maximun(tree: Tree[Int]): Int = {
    tree match {
      case Leaf(value) => value
      case Branch(l, r) => maximun(l) max maximun(r)
    }
  }

  def depth[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 0
      case Branch(l, r) => 1 + (depth(l) max depth(r))
    }
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
    tree match {
      case Leaf(value) => Leaf(f(value))
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }
  }


  def fold[A, B](tree: Tree[A])(f: A => B)(g: (B, B) => B): B = {
    tree match {
      case Leaf(a) => f(a)
      case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
    }
  }

  def sizeViaFold[A](tree: Tree[A]): Int = {
    fold(tree)(a => 1)(1 + _ + _)
  }

  def maximunViaFold(tree: Tree[Int]): Int = {
    fold(tree)(a => a)(_ max _)
  }

  def depthViaFold[A](tree: Tree[A]): Int = {
    fold(tree)(a => 0)((d1, d2) => 1 + (d1 max d2))
  }

  def mapViaFold[A, B](tree: Tree[A])(f: A => B): Tree[B] = {
    fold(tree)(a => Leaf(f(a)): Tree[B])((d1, d2) => Branch(d1,d2))
  }

}