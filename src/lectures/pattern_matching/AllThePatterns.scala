package lectures.pattern_matching

import lectures.part3_functional_programming.myCollection.{Cons, Empty, MyGenericList}

/**
  * Created by Tomohiro on 10 juillet 2019.
  */

object AllThePatterns extends App {

  // 1 - Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "Int"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "a singleton object"
  }

  // 2 - match anything
  // 2.1 Wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variables
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case (something,2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // Pattern Matches can be NESTED!!

  // 4 - Case Classes - constructor pattern
  // Patter Matches can be nested with Case Classes as well
  val aList: MyGenericList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - List patterns
  var aStandardList = List(1,2,3,42)
  var standardListMatching = aStandardList match {
    case List(1,_,_,_) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42 => // infix pattern
  }

  // 6 - Type Specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_,_) => // name binding => use the name later (here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // Compound pattern (multi-pattern)
    case _ =>
  }

  // 9 - if guards
  val aSecondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    case _ =>
  }

}
