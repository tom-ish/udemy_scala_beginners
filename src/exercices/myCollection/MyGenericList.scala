package exercices.myCollection


abstract class MyGenericList[+A] {

  /*
    head = first element of the list
    tail = reminder of the list (pointer)
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head : A
  def tail : MyGenericList[A]
  def isEmpty : Boolean
  def add[B >: A](e: B) : MyGenericList[B]
  def printElements : String
  override def toString : String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]) : MyGenericList[B]
  def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]) : MyGenericList[B]
  def filter(predicate: MyPredicate[A]) : MyGenericList[A]

  // concatenation
  def ++[B >: A](list: MyGenericList[B]) : MyGenericList[B]
}

case object Empty extends MyGenericList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyGenericList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](e: B) : MyGenericList[B] = new Cons(e, Empty)
  override def printElements: String = " "

  override def map[B](transformer: MyTransformer[Nothing, B]): MyGenericList[B] = Empty
  override def flatMap[B](transformer: MyTransformer[Nothing, MyGenericList[B]]) : MyGenericList[B] = Empty
  override def filter(predicate: MyPredicate[Nothing]) : MyGenericList[Nothing] = Empty

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

  override def map[B](transformer: MyTransformer[A, B]) : MyGenericList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  override def filter(predicate: MyPredicate[A]) : MyGenericList[A] =
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)


  override def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyGenericList[B]]) : MyGenericList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

}


