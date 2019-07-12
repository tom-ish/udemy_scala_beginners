package lectures.part3_functional_programming

import scala.util.Random

/**
  * Created by Tomohiro on 08 juillet 2019.
  */

object Sequence extends App {

  // Sequence
  val seq = Seq(1,3,2,4)
  println(seq)
  println(seq.reverse)
  println(seq(2))
  println(seq ++ Seq(7,5,6))
  println(seq.sorted)

  // Ranges
  val aRange : Seq[Int] = 1 until 10
  aRange.foreach(println)

  // List
  val aList = List(1, 2, 3, 4)
  val prepended = 42 :: aList
  println(prepended)

  val appended = 42 +: aList :+ 24
  println(appended)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("="))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)

  threeElements.foreach(println)

  // Mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq : Seq[Int] = numbers // implicit conversion

   // Vectors
  val vector : Vector[Int] = Vector(1, 2, 3)
  println(vector)


  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection : Seq[Int]) : Double = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val now = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - now
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
