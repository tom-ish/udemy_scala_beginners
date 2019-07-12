package lectures.pattern_matching

import scala.util.Random

/**
  * Created by Tomohiro on 09 juillet 2019.
  */

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "blabla"
    case 2 => "double or nothing"
    case 3 => "third"
    case _ => "Something else called a WILDCARD"
  }

  println(x)
  println(description)


  // 1. Decompose value
  case class Person(name: String, age: Int)
  val Bob = new Person("Bob", 20)

  val greeting = Bob match {
    case Person(name, age) if(age < 21) => s"Hi my name is $name and I can't drink in the US"
    case Person(name, age) => s"Hi my name is $name and I am $age years old"
    case _ => "Hi"
  }

  println(greeting)



  /*
      1. case are matched in ordered
      2. what if no cases matched? MatchError => wildcard
      3. type of the pattern matching expression ? unified type of all the types in all the cases
      4. Pattern Matching works really well with case classes
   */

  // Pattern Matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched Dog of the $someBreed breed")
  }

  /*
      Exercices
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def read(expr: Expr) : String = expr match {
      case Number(n) => s"$n"
      case Sum(e1, e2) => read(e1) + " + " + read(e2)
      case Prod(e1, e2) => {
        def maybeShowParenthesis(e: Expr) = e match {
          case Prod(_, _) => read(e)
          case Number(_) => read(e)
          case _ => s"(" + read(e) + ")"
        }
        maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
      }
  }

  println(read(Sum(Number(2), Number(3))))
  println(read(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(read(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(read(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  println(read(Sum(Prod(Number(2), Number(1)), Number(3))))
}
