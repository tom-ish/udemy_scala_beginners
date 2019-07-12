package exercices.myCollection

trait MyTransformer[-A, B] {
  def transform(arg: A) : B
}
