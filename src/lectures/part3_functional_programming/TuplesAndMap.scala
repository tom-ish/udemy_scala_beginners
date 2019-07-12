package lectures.part3_functional_programming

/**
  * Created by Tomohiro on 08 juillet 2019.
  */

object TuplesAndMap extends App {

  // tuples = finite ordered "lists"
  val aTuple = (2, "hello scala") // Tuple[Int, String] = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap) // ("hello scala", 2)

  // Maps - keys -> value
  val aMap : Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is syntax sugar for (a, b)
  println(phoneBook)

  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)


  // functionals on maps
  // map, flatmap, filter
  println(newPhoneBook.map((pair : (String, Int)) => pair._1.toLowerCase -> pair._2))

  // filterKeys
  val filterKey = newPhoneBook.view.filterKeys(x => x.startsWith("J")).toMap
  val mapValue = newPhoneBook.view.mapValues(key => key * 10).toMap

  println(filterKey)
  println(mapValue)


  // conversion to other collections
  println(newPhoneBook.toList)
  println(List(("Dan", 123)).toMap)

  val names = List("James", "Bob", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))


  val exercices = Map("Jim" -> 123, "Mary" -> 456, "Bob" -> 789, "JIM" -> 900)
  println(exercices)

  println(exercices.map((pair : (String, Int)) => pair._1.toLowerCase -> pair._2))









  def add(network: Map[String, Set[String]], person: String) : Map[String, Set[String]] = {
    if(network.contains(person)) throw new RuntimeException(person + " already exists")
    else network + (person -> Set())
  }

  // doesn't work
  def remove(network: Map[String, Set[String]], person: String) : Map[String, Set[String]] = {
    val userFriends = network(person)
    for {
      friend <- userFriends
    } network + (friend -> (network(friend) - person))
    network - person
   }

  def delete(network: Map[String, Set[String]], person: String) : Map[String, Set[String]] = {
    def deleteAux(friends : Set[String], networkAcc : Map[String, Set[String]]) : Map[String, Set[String]] = {
      if(friends.isEmpty) networkAcc
      else
        deleteAux(friends.tail, unfriend(networkAcc, friends.head, person))
    }

    val unfriended = deleteAux(network(person), network)
    unfriended - person
  }


  def friend(network: Map[String, Set[String]], a: String, b: String) : Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    if(friendsA.contains(b) || friendsB.contains(a))
      throw new RuntimeException(b + " is already friend with " + a)
    else
      network + (a -> (friendsA + b)) + (b -> (friendsB + a))


  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String) : Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    if(friendsA.contains(b) && friendsB.contains(a)) {
      network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }
    else
      throw new RuntimeException(b + " is already unfriend with " + a)
  }



  val empty = Map[String, Set[String]]()
  val network = add(add(add(empty, "tomo"), "Bob"), "Mary")

  println(network)

  println(friend(network, "Bob", "Mary"))
  val newNetwork = friend(friend(network,"Bob", "tomo"), "Bob", "Mary")
  println(delete(newNetwork, "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], a: String): Int =
    if(!network.contains(a)) 0
    else network(a).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]) : String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleNoFriend(network: Map[String, Set[String]]) : Int =
    //network.count(pair => pair._2.isEmpty)
    network.view.filterKeys(key => network(key).isEmpty).size

  println(nPeopleNoFriend(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String) : Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]) : Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(target == person) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Bob"))

}
