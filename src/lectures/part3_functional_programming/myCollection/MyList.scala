package lectures.part3_functional_programming.myCollection

object MyList {}
/*
abstract class MyList {

  /*
    head = first element of the list
    tail = reminder of the list (pointer)
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head : Int
  def tail : MyList
  def isEmpty : Boolean
  def add(e: Integer) : MyList
  def printElements : String
  override def toString : String = "[" + printElements + "]"

}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(e: Integer): MyList = new Cons(e, this)
  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(e: Integer): MyList = new Cons(h, this)
  override def printElements: String =
    if(t.isEmpty) h+""
    else h + "" + t.printElements
}
*/