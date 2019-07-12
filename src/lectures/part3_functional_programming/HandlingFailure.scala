package lectures.part3_functional_programming

import java.util.Random

import scala.util.{Failure, Success, Try}

/**
  * Created by Tomohiro on 09 juillet 2019.
  */

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try object via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) // .isFailure

  // orElse
  def backupMethod() : String = "a valid result"
  val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)

  // if you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("a valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatmap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))


  // Exercices
  val url = "..."
  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String) : String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String) : Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))

  }

  println("-------- Exercices --------")

  // if you get the html page from the connection, print it in the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(c => c.getSafe(url))
  possibleHTML.foreach(renderHTML)

  println("===============")
  HttpService.getSafeConnection(host, port)
    .flatMap(_.getSafe(url))
    .foreach(renderHTML)

  println("===============")

  val connectionStatus = Try(HttpService.getConnection(host, port))
    .flatMap(c => Try(c.get(url)))
    .foreach(page => Try(renderHTML(page)))


  val forConnectionStatus = for {
    c <- Try(HttpService.getConnection(host, port))
    page <- Try(c.get(url))
  } renderHTML(page)

  /*
    try {
      connection = HttpService.getConnection(host, port)
      try {
        connection.get(url)
        renderHTML(page)
      }
      catch (some other exception)
    }
    catch (exception)
   */
}
