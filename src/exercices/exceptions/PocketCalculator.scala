package exercices.exceptions

object PocketCalculator {

  def add(x: Int, y: Int) : Int = {
    val result = x+y
    if(x>0 && y>0 && result<0) throw new OverflowException
    else if(x<0 && y<0 && result>0) throw new UnderflowException
    else result
  }

  def sub(x: Int, y: Int) : Int = {
    val result = x-y
    if(x<0 && y>0 && result>0) throw new UnderflowException
    else if(x>0 && y<0 && result<0) throw new OverflowException
    else result
  }
  def mul(x: Int, y: Int) : Int = {
    val result = x*y
    if(x>0 && y>0 && result<0) throw new OverflowException
    else if(x<0 && y<0 && result<0) throw new OverflowException
    else if(x>0 && y<0 && result>0) throw new UnderflowException
    else if(x<0 && y>0 && result>0) throw new UnderflowException
    else result
  }
  def div(x: Int, y: Int) : Int =
    if(y == 0) throw new MathCalculationException
    else x / y


  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

}
