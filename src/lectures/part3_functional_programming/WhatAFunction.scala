package lectures.part3_functional_programming

import lectures.part3_functional_programming.myCollection.{Cons, Empty, MyGenericList}

/**
  * Created by Tomohiro on 03 juillet 2019.
  */

object WhatAFunction extends App {

  def concatenator : (String, String) => String = new Function2[String, String, String] {
    override def apply(s1: String, s2: String) : String = s1 + s2
  }

  println(concatenator("a", "B"))



  val listOfIntegers : MyGenericList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers : MyGenericList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings : MyGenericList[String] = new Cons("Hello", new Cons("Scala", new Cons("Tomo", Empty)))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }))

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(arg: Int): Boolean = arg%2 == 1
  }))

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new Function1[Int, MyGenericList[Int]] {
    override def apply(arg: Int): MyGenericList[Int] = new Cons(arg, new Cons(arg+1, Empty))
  }))
}
