package lectures.part3_functional_programming

/**
  * Created by Tomohiro on 07 juillet 2019.
  */

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)

  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.map(_ % 2 == 0))

  // flatmap
  println(list.flatMap((x: Int) => List(x, x+1)))

  val chars = List('a','b','c','d')
  val numbers = List(1, 2, 3, 4)


  val mixCI = numbers.flatMap(n => chars.map(c => "" + c + n))
  val mixIC = chars.flatMap(c => numbers.map(n => "" + c + n))

  val mixCI_ = numbers.map(n => chars.flatMap(c => "" + c + n))
  val mixIC_ = chars.map(c => numbers.flatMap(n => "" + c + n))

  println(mixCI)
  println(mixCI_)
  println(mixIC)
  println(mixIC_)

 }


