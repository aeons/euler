package euler.solvers

object ProblemSolver001 extends ProblemSolver {
  override def solve(): Int = {
    (1 until 1000).filter(i => i % 3 == 0 || i % 5 == 0).sum
  }
}