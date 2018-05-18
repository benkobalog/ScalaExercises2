package variance

class Vehicle
class Car extends Vehicle
class Opel extends Car

object Main {
  def main(args: Array[String]): Unit = {

    val cars = List(new Car, new Opel)
    printBox(new CoBox[Car](cars))

    def printBox(box: CoBox[Vehicle]): Unit = {
      println(box.get)
    }

  }

  class CoBox[+A <: Vehicle](vehicles: List[A]) {

    def get: Option[A] = vehicles.headOption

//    def put(vehicle: A)
  }
}
