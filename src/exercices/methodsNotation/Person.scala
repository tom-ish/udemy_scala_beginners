package exercices.methodsNotation

class Person (name: String, val age: Int, movie: String) {

  def +(s: String) : Person = new Person(name + " " + s, age, movie)
  def unary_+ : Person = new Person(name, this.age+1, movie)

  def learns(s: String) : String = name + " learns " + s
  def learnsScala(): String = this learns "scala"

  def apply(n: Int): String = name + " watched " + movie + " " + n + " times"
}
