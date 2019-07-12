package lectures.pattern_matching

/**
  * Created by Tomohiro on 10 juillet 2019.
  */

object PatternsEverywhere extends App {

  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES
  /*
    try {
      // code
    } catch (e) {
      e match {
        case e: RuntimeException => "Runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
      }
    }
   */

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield x * 10

  // generators are also based on PATTERN MATCHING
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // other patterns available : Case Classes, :: operators, ...

  // big idea #3
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  println(b)

  // multiple value definition based on PATTERN MATCHING
  // ALL THE POWER IS AVAILABLE
  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4 - NEW
  // Partial Function
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function litteral

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

  println(mappedList)
}
