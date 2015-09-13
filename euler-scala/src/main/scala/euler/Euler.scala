package euler

import euler.solvers.ProblemSolver

object Euler {
  def main(args: Array[String]): Unit = {
    val n = 2
    euler(n)
  }

  private def euler(n:Int): Any = {
    val p :Problem = Problem.loadFromFile("C:/code/euler/project_euler.txt", n).getOrElse {
      println(s"Could not load problem $n.")
      return Unit
    }

    println(s"Project Euler - Problem ${p.number}")
    println()
    println(p.text)
    println()

    val answer = ProblemSolver.solveWithTime(p).getOrElse{
      println(s"No solver found for problem $n.")
      return Unit
    }

    print(s"Answer found: $answer -- ")
    p.check(answer) match {
      case true => println("CORRECT!")
      case false => println("Not correct.")
    }
  }
}


