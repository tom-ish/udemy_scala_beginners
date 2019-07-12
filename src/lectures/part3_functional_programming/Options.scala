package lectures.part3_functional_programming

import scala.util.Random

/**
  * Created by Tomohiro on 09 juillet 2019.
  */

object Options extends App {
  val myFirstOption : Option[Int] = Some(4)
  val noOption : Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // WORK with unsafe APIs
  def unsafeMethod() : String = null
  // val result = Some(unsafeMethod()) => WRONG because Some(null)
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod() : String = "a valid result"
  val chainedResult = Option(unsafeMethod()) orElse Option(backupMethod())

  // DESIGN unsafe APIs
  def betterUnsafeMethod() : Option[String] = None
  def betterBackupMethod() : Option[String] = Some("a valid result")
  var betterChainedResult = Option(betterUnsafeMethod()) orElse Option(betterBackupMethod())

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flat, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions

  // Exercices
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")


  /*
    if (h != null)
      if (p != null)
        return Connection(h, p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))

  /*
    if (c != null)
      return c.connect()
    return null
   */
  val connectionStatus = connection.map(_.connect)

  /*
    if (connectionStatus == null) println(None)
    else println(Some(connectionStatus.get))
   */
  println(connectionStatus)

  /*
    if (status != null) println(status)
   */
  connectionStatus.foreach(println)



  // Chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
  .foreach(println)


  // for-comprehension
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
