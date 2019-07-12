package exercices.poo

class Writer (firstname: String, surname: String, var year: Int) {

  def fullname() : String = firstname + " " + surname

}