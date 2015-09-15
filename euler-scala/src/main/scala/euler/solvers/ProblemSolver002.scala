package euler.solvers

import euler.solvers.Util.fib

object ProblemSolver002 extends ProblemSolver {
  override def solve(): Int = {
    fib takeWhile (f => f < 4000000) filter (f => f % 2 == 0) sum
  }
}