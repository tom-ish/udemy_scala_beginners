package lectures.part3_functional_programming

/**
  * Created by Tomohiro on 07 juillet 2019.
  */

object AnonymousFunctions extends App {

  val superAdder2: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function1[Int, Int] {
      override def apply(v2: Int) : Int = v1 + v2
    }
  }

  val superAdder = (x : Int) => (y: Int) => x + y

  val superAdderBis : Int => (Int => Int) = (y: Int) => _ + y

  println(superAdder2(3)(4))

  println(superAdder(3)(4))

  println(superAdderBis(3)(4))

}
