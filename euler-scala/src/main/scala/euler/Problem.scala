package euler

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

import scala.io.Source
import scala.util.control.NonFatal

class Problem(val number: Int, val text: String, val answerHash: String) {
  def check(answer: Int): Boolean = md5(answer) == answerHash

  private def md5(i: Int) = MessageDigest
    .getInstance("MD5")
    .digest(i.toString.getBytes())
    .map("%02x".format(_))
    .mkString
}

object Problem {
  private val number = """\d+""".r
  private val md5Hash = """[a-f0-9]{32}""".r

  def loadFromFile(file: String, n: Int): Option[Problem] = {
    try {
      val pt = loadProblemText(file, n)
      val p = parseProblem(pt)
      Option(p)
    } catch {
      case NonFatal(exc) => None
    }
  }

  private def parseProblem(ls: Iterable[String]): Problem = {

    val n = (number findFirstIn ls.head get).toInt
    val t = ls.drop(4).dropRight(5).map(_.trim).mkString("\n")
    val h = (md5Hash findFirstIn ls.takeRight(3).head).get
    new Problem(n, t, h)
  }

  private def loadProblemText(file: String, n: Int): Iterable[String] = {
    Source
      .fromFile(file)(StandardCharsets.UTF_8)
      .getLines
      .dropWhile(!_.startsWith(f"Problem $n"))
      .takeWhile(!_.startsWith(f"Problem ${n + 1}")).toIterable
  }
}