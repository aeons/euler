package euler.solvers

object Util {
  def fib(): Iterator[Int] = {
    Iterator.iterate((1, 2)) { case (a, b) => (b, a + b) }.map(_._1)
  }
}
