package euler.solvers

import euler.Problem
import euler.exceptions.ProblemSolverNotFoundException

import scala.util.control.NonFatal

trait ProblemSolver {
  def solve(): Int
}

object ProblemSolver {
  def solve(p: Problem): Option[Int] = {
    try {
      Option(solverForProblem(p).solve())
    } catch {
      case NonFatal(ex) => None
    }
  }

  def solveWithTime(p: Problem): Option[Int] = {
    try {
      val ps = solverForProblem(p)
      val res = time(ps.solve())
      Option(res)
    } catch {
      case NonFatal(ex) => None
    }
  }

  private def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val res = block
    val t1 = System.nanoTime()
    println(f"Answer found in ${(t1 - t0) / 1000000.0}%.2f ms.")
    res
  }

  def solverForProblem(p: Problem): ProblemSolver = {
    try {
      companion[ProblemSolver](f"euler.solvers.ProblemSolver${p.number}%03d")
    } catch {
      case NonFatal(ex) => throw ProblemSolverNotFoundException
    }
  }

  private def companion[T](name: String) = {
    Class.forName(name + "$").getField("MODULE$").get(null).asInstanceOf[T]
  }
}




