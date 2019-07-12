package exercices.poo

import java.util.Calendar

class Novel(name: String, releaseYear: Int, author: Writer) {
  def authorAge() : Int = releaseYear - author.year

  def isWrittenBy(newAuthor: Writer) : Boolean = this.author == newAuthor

  def copy(newReleaseYear: Int) : Novel = new Novel(name, newReleaseYear, author)

}
