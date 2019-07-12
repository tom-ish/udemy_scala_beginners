package lectures.part3_functional_programming.myCollection


abstract class MyGenericList[+A] {

  def head : A
  def tail : MyGenericList[A]
  def isEmpty : Boolean
  def add[B >: A](e: B) : MyGenericList[B]
  def printElements : String
  override def toString : String = "[" + printElements + "]"

  def map[B](transformer: A => B) : MyGenericList[B]
  def flatMap[B](transformer: A => MyGenericList[B]) : MyGenericList[B]
  def filter(predicate: A => Boolean) : MyGenericList[A]

  // hofs
  def foreach(f: A => Unit) : Unit
  def sort(compare : (A, A) => Int) : MyGenericList[A]
  def zipWith[B, C](list: MyGenericList[B], f: (A, B) => C) : MyGenericList[C]
  def fold[B](start: B)(f: (A, B) => B) : B

  // concatenation
  def ++[B >: A](list: MyGenericList[B]) : MyGenericList[B]
}

case object Empty extends MyGenericList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyGenericList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](e: B) : MyGenericList[B] = new Cons(e, Empty)
  override def printElements: String = " "

  override def map[B](transformer: Nothing => B): MyGenericList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyGenericList[B]) : MyGenericList[B] = Empty
  override def filter(predicate: Nothing => Boolean) : MyGenericList[Nothing] = Empty

  override def foreach(f: Nothing => Unit): Unit = ()
  override def sort(f: (Nothing, Nothing) => Int) : MyGenericList[Nothing] = Empty
  override def zipWith[B, C](list: MyGenericList[B], f: (Nothing, B) => C): MyGenericList[C] =
    if(!list.isEmpty) throw new RuntimeException("list do not have the same length")
    else Empty

  override def fold[B](start: B)(f: (Nothing, B) => B): B = start

  override def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list
}

case class Cons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  override def head: A = h
  override def tail: MyGenericList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](e: B) : MyGenericList[B] = new Cons(e, this)
  override def printElements: String =
    if(t.isEmpty) h+""
    else h + " " + t.printElements

  override def map[B](transformer: A => B) : MyGenericList[B] =
    new Cons(transformer(h), t.map(transformer))

  override def filter(predicate: A => Boolean) : MyGenericList[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int) : MyGenericList[A] = {
    def insert(x: A, sortedList: MyGenericList[A]) : MyGenericList[A] = {
      if(sortedList.isEmpty) new Cons(x, Empty)
      else if(compare(x, sortedList.head) > 0)
        new Cons(sortedList.head, insert(x, sortedList.tail))
      else
        new Cons(x, sortedList)
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyGenericList[B], f: (A, B) => C): MyGenericList[C] = {
    if(list.isEmpty) throw new RuntimeException("list do not have the same length")
    else
      new Cons(f(h, list.head), t.zipWith(list.tail, f))
  }

  override def fold[B](start: B)(f: (A, B) => B): B =
    t.fold(f(h, start))(f)


  override def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyGenericList[B]) : MyGenericList[B] =
    transformer(h) ++ t.flatMap(transformer)

}


object MyGenericListTest extends App {
  val listOfIntegers : MyGenericList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers : MyGenericList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings : MyGenericList[String] = new Cons("Hello", new Cons("Scala", new Cons("Tomo", Empty)))

  println(listOfIntegers)
  println(listOfStrings)

  /*
  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }))
   */
  println(listOfIntegers.map(a => a * 2))

/*
  println(listOfIntegers.filter(new Function1[Int,Boolean] {
    override def apply(arg: Int): Boolean = arg%2 == 1
  }))
 */

  println(listOfIntegers.filter(a => a%2 ==1))

  println(listOfIntegers ++ anotherListOfIntegers)

  /*
  println(listOfIntegers.flatMap(new Function1[Int, MyGenericList[Int]] {
    override def apply(arg: Int): MyGenericList[Int] = new Cons(arg, new Cons(arg+1, Empty))
  }))
   */

  println(listOfIntegers.flatMap(x => new Cons(x, new Cons(x+1, Empty))))

  println("======")

  listOfStrings.foreach(x => println(x))

  println("-----")
  println(listOfIntegers.sort((x, y) => y - x))

  println("-----")

  println(listOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println("XXXXXXX")

  println(listOfIntegers.fold(0)(_ + _))


  println("FOR COMPREHENSION")

  val combi = for {
    n <- listOfIntegers
    s <- listOfStrings
  } yield s + "-" + n

  println(combi)

}

