package exercices.myCollection

trait MyPredicate[-T] {
  def test(arg: T) : Boolean
}
