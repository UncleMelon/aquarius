package fpinscala.charpter3

import fpinscala.charpter3.List.{product, sum}

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List {

  def sum(ints: List[Int]): Int = {
    ints match {
      case Nil => 0
      case Cons(x, xs) => x + sum(xs)
    }
  }

  def product(ds: List[Double]): Double = {
    ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x, xs) => x * product(xs)
    }
  }

  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def tail[A](l: List[A]): List[A] = {
    l match {
      case Cons(head, tail) => tail
      case Nil => throw new UnsupportedOperationException("tail of empty list")
    }
  }

  def setHead[A](head: A, l: List[A]): List[A] = {
    l match {
      case Cons(h, tail) =>  Cons(head, tail)
      case Nil => apply(head)
    }
  }

  def drop[A](l: List[A], n: Int): List[A] = {
    n match {
      case 0 => l
      case _ => drop(tail(l), n-1)
    }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Cons(head, tail) if f(head) => dropWhile(tail, f)
      case Cons(head, tail) => Cons(head, dropWhile(tail, f))
      case _ => Nil
    }
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = {
    a1 match {
      case Nil => a2
      case Cons(head, tail) => Cons(head, append(tail, a2))
    }
  }

  def init[A](l: List[A]): List[A]= {
    l match {
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }
  }

  def dropWhileC[A](as: List[A])(f: A => Boolean):List[A] = {
      as match {
        case Cons(h, t) if f(h) => dropWhileC(t)(f)
        case _ => as
      }
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) = {
    foldRight(ns, 0)((x,y) => x+y)
  }

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def length[A](as: List[A]): Int = {
    foldRight(as, 0)((x,y) => y+1)
  }

  // left 从左到右 b + a => b
  // right 从右到左 a + b => b
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }
  }

  def sum3(ns: List[Int])  = {
    foldLeft(ns, 0)(_+_)
  }

  def produce3(ns: List[Double]) = {
    foldLeft(ns, 1.0)(_*_)
  }

  def length_left[A](ns: List[A]) = {
    foldLeft(ns, 0)((x, y) => x + 1)
  }

  def fold[A](as: List[A]): List[A] = {
    foldLeft(as, Nil:List[A])((x,y) =>Cons(y,x))
  }

  def foldRightviafoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(fold(as), z)((b, a) => f(a, b))
    }

  def foldRightviafoldLeft_2[A, B](as: List[A], z:B)(f: (A, B) => B): B = {
    foldLeft(as, (b:B) => b)((g,a) => b => g(f(a,b)))(z)
  }

  def foldLeftviafoldRight_2[A, B](as: List[A], z:B)(f: (B, A) => B): B = {
    foldRight(as, (b:B) => b)((a,g) => b => g(f(b, a)))(z)
  }

  def append1[A](a1: List[A], a2: List[A]): List[A] = {
    foldRight(a1, a2)(Cons(_,_))
  }

  def joinlist[A](lists: List[List[A]]): List[A] = {
    foldRight(lists, Nil: List[A])(append1)
  }

  def add1(list: List[Int]): List[Int] = {
    foldRight(list, Nil: List[Int])((h,t) => Cons(h+1, t))
  }

  def Double2String(lists: List[Double]): List[String] = {
    foldRight(lists, Nil: List[String])((h, t) => Cons(h.toString, t))
  }

  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldRight(as, Nil: List[B])((h, t) => Cons(f(h), t))
  }

  def filter[A](as: List[A])(f:A => Boolean): List[A] = {
    foldRight(as, Nil: List[A])((h, t) => if(f(h)) Cons(h, t) else t)
  }

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
    joinlist(map(as)(f))
  }

  def filter2[A](as: List[A])(f:A => Boolean): List[A] = {
    flatMap(as)(a => if (f(a)) List(a) else Nil)
  }

  def listsadd(a1: List[Int], a2: List[Int]): List[Int] = {
    (a1, a2) match {
      case (_, Nil) => Nil
      case (Nil, _) => Nil
      case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1+h2, listsadd(t1, t2))
    }
  }

  def zipWith[A, B, C](a1: List[A], a2: List[B])(f: (A, B) => C): List[C] = {
    (a1, a2) match {
      case (_, Nil) => Nil
      case (Nil, _) => Nil
      case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    }
  }

  def startWith[A](l: List[A], prefix: List[A]): Boolean = {
    (l, prefix) match {
      case (_, Nil) => true
      case (Cons(h1, t1), Cons(h2, t2)) if(h1 == h2)  => startWith(t1, t2)
      case _ => false
    }
  }

  def hasSubsequence[A] (sup: List[A], sub: List[A]): Boolean = {
    sup match {
      case Nil => sub == Nil
      case _ if startWith(sup, sub) => true
      case Cons(h, t) => hasSubsequence(t, sub)
    }
  }



}

object Test {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    val double = List(0.1, 0.2, 0.3, 0.4)
    println(sum(list))
    println(product(double))
    val x = List(1,2,3,4,5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons (x, Cons(y, Cons(3, Cons(4,_)))) => x+y
      case Cons(h, t) => h + sum(t)
      case _ => 101
    }
    println(x)

    println(List.tail(List(1,2,3,4,5)))
    println(List.setHead(1, Nil))
    println(List.setHead(3, List(1,2,3,4,5)))
    println(List.drop(List(1,2,3,4,5), 4))
    println(List.dropWhile(List(1,2,3,4,5,4), (x: Int) => x == 4))
    println(List.init(List(1,2,3,4,5)))
    println(List.dropWhileC(List(1,2,3,4,5))(x => x < 4))
    println(List.length(List(1,2,3,4,5,6,7,8,9,10)))
    println(List.foldLeft(List(1,2,3,4), 100)(_+_))
    println(List.fold(List(1,2,3,4)))
  }
}
