package lectures.part3_functional_programming

/**
  * Created by Tomohiro on 07 juillet 2019.
  */

object HOFsCurries extends App {

  def toCurry(f: (Int, Int) => Int) : Int => Int => Int =
    x => y => f(x, y)


  def fromCurry(f: (Int => Int => Int)) : (Int, Int) => Int =
    (x, y) => f(x)(y)

  def compose(f: Int => Int)(g: Int => Int) : Int => Int =
    x => f(g(x))


  def andThen(f: Int => Int)(g: Int => Int) : Int => Int =
    x => g(f(x))
}
