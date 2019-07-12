package exercices.exceptions

object Exceptions extends App {

  val errorId = 2
  try {
    crashTest(errorId)
  }
  catch {
    case e : OutOfMemoryError => println("OutOfMemoryError")
    case e : StackOverflowError => println("StackOverflowError")
  }
  finally {
    println("finally")
  }

  def crashTest(id : Int) = {
    if (id == 1)
      Array.ofDim(Int.MaxValue)
    else if (id == 2)
      infinite
  }

  def infinite : Int = 1 + infinite
}
