package exercices

/**
  * Created by Tomohiro on 08 juillet 2019.
  */

abstract class Maybe[+T] {
  def map[B](f: T => B) : Maybe[B]
  def flatmap[B](t: T => Maybe[B]) : Maybe[B]
  def filter(predicate : T => Boolean) : Maybe[T]
}

case object MaybeNot extends Maybe[Nothing] {
  override def map[B](f: Nothing => B): Maybe[B] = MaybeNot
  override def flatmap[B](t: Nothing => Maybe[B]): Maybe[B] = MaybeNot
  override def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+T](value: T) extends Maybe[T] {
  override def map[B](f: T => B): Maybe[B] = Just(f(value))
  override def flatmap[B](t: T => Maybe[B]): Maybe[B] = t(value)
  override def filter(predicate: T => Boolean): Maybe[T] =
    if(predicate(value)) this
    else MaybeNot
}

object Maybe extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatmap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 0))
}