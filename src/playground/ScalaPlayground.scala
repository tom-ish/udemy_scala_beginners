package playground

import exercices.methodsNotation.Person
import exercices.myCollection.{Cons, Empty, MyGenericList, MyPredicate, MyTransformer}
import exercices.poo.Writer

import scala.language.postfixOps


object ScalaPlayground extends App {

  println("Hello Scala")

  def helloFunction(name: String, age: Integer) : String = {
    "hi! my name is " + name + " and I am " + age + " years old"
  }

  def factorial(n: Integer) : Integer = {
    if(n == 1) 1
    else n * factorial(n-1)
  }

  def fibonacci2(n: Integer) : Integer = {
    if(n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  /*
  def isPrime(n: Integer) : Boolean = {
    def isPrimeUntil(n: Integer, m: Integer) : Integer = {

    }
  }
   */

  println(helloFunction("tomo", 25))
  println("factorial : " + factorial(5))
 // println("fibonacci : " + fibonacci(6))


  def concatenate(s: String, n: Integer, acc: String) : String = {
    if(n <= 0) acc
    else concatenate(s, n-1, s + acc)
  }

  println(concatenate("Yo ", 2, ""))

  def fibonacci(n: Integer) : Integer = {
    def fibonacciTailRec(m: Integer, last: Integer, beforeLast: Integer): Integer = {
      if(m <= 2) last
      else fibonacciTailRec(m-1, last + beforeLast, last)
    }

    fibonacciTailRec(n, 1, 1)
  }

  println(fibonacci(7))



  val w = new Writer("tomo", "toto", 2019)
  println(w.fullname())


  val p = new Person("tomo", 26, "Inception")
  println(p + "the rockstar")
  println(p.+("the rockstar"))
  println((+p).age)
  println(p learnsScala)
  println(p(5))




  val listOfIntegers : MyGenericList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers : MyGenericList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings : MyGenericList[String] = new Cons("Hello", new Cons("Scala", new Cons("Tomo", Empty)))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(arg: Int): Int = arg * 2
  }))

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(arg: Int): Boolean = arg%2 == 1
  }))

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyGenericList[Int]] {
    override def transform(arg: Int): MyGenericList[Int] = new Cons(arg, new Cons(arg+1, Empty))
  }))
}
