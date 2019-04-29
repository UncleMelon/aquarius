package fpinscala.charpter1

import Cafe.{Charge, Coffee, CreditCard}


class Cafe {
  def buyCoffeewithSideEffect(cc: CreditCard): Coffee = {
    val coffee = new Coffee()
    cc.charge(coffee.price)     // have side effect
    coffee
  }

  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffee = new Coffee()
    (coffee, Charge(cc, coffee.price))
  }

  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees, charges) = purchases.unzip
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  def coalesce(charges: List[Charge]) = {
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
  }

}

object Cafe {
  case class CreditCard() {
    def charge(price: Double): Unit = {}
  }

  case class Coffee() {
    val price = 5.0
  }

  case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge) = {
      if (cc == other.cc) {
        Charge(cc, amount + other.amount)
      } else
        throw new Exception("can't combine charges to different cards")
    }
  }
}
