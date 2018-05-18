package variance

import Helper._

class Vehicle
class Car extends Vehicle
class Opel extends Car

object Main {
  private val cars = List(new Car, new Opel)
  private val vehicles = new Vehicle :: cars

  def main(args: Array[String]): Unit = {
    val declBox1 = new DeclarationSiteCovariantBox(vehicles)
    val declBox2: DeclarationSiteCovariantBox[Car] = new DeclarationSiteCovariantBox(cars)
    println(declBox2.get)

    isSubtype(declBox2, declBox1)

    val useSiteBox1: Box[_ <: Vehicle] = new Box()
    val useSiteBox2: Box[_ <: Car] = new Box()
//    softDrinkBox.put(new Opel)
    val car: Car = useSiteBox2.retrieve
    println(car)

//    isSubtype(useSiteBox2, useSiteBox1)
  }
}

class DeclarationSiteCovariantBox[+A <: Vehicle](vehicles: List[A]) {

  def get: Option[A] = vehicles.headOption

  // This doesn't compile.
//      def put(vehicle: A) = { // do something }
}

class Box[A]() {

  private var thing: A = _

  def retrieve: A = thing

  def put(thing: A): Unit = this.thing = thing
}

object Helper {
  def isSubtype[A, B](a: A, b: B)(implicit evidence: A <:< B) = true
}