package exercices.poo

class Counter (n: Int) {
  def currentCount(): Int = n
  def increment(): Counter = new Counter(this.n + 1)
  def increment(amount: Int): Counter = new Counter(this.n + amount)
  def decrement(): Counter = new Counter(this.n - 1)
  def decrement(amount: Int): Counter = new Counter(this.n - amount)
}
