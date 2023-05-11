package com.maxrt.data

import scala.util.Random
import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

object RandomDataGenerator {
  private val ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
  private val FIRST_NAMES = Array("Abigail", "Alexandra", "Alison", "Amanda", "Amelia", "Amy", "Andrea", "Angela", "Anna", "Anne", "Audrey", "Ava", "Bella", "Bernadette", "Carol", "Caroline", "Carolyn", "Chloe", "Claire", "Deirdre", "Diana", "Diane", "Donna", "Dorothy", "Elizabeth", "Ella", "Emily", "Emma", "Faith", "Felicity", "Fiona", "Gabrielle", "Grace", "Hannah", "Heather", "Irene", "Jan", "Jane", "Jasmine", "Jennifer", "Jessica", "Joan", "Joanne", "Julia", "Karen", "Katherine", "Kimberly", "Kylie", "Lauren", "Leah", "Lillian", "Lily", "Lisa", "Madeleine", "Maria", "Mary", "Megan", "Melanie", "Michelle", "Molly", "Natalie", "Nicola", "Olivia", "Penelope", "Pippa", "Rachel", "Rebecca", "Rose", "Ruth", "Sally", "Samantha", "Sarah", "Sonia", "Sophie", "Stephanie", "Sue", "Theresa", "Tracey", "Una", "Vanessa", "Victoria", "Virginia", "Wanda", "Wendy", "Yvonne", "Zoe", "Adam", "Adrian", "Alan", "Alexander", "Andrew", "Anthony", "Austin", "Benjamin", "Blake", "Boris", "Brandon", "Brian", "Cameron", "Carl", "Charles", "Christian", "Christopher", "Colin", "Connor", "Dan", "David", "Dominic", "Dylan", "Edward", "Eric", "Evan", "Frank", "Gavin", "Gordon", "Harry", "Ian", "Isaac", "Jack", "Jacob", "Jake", "James", "Jason", "Joe", "John", "Jonathan", "Joseph", "Joshua", "Julian", "Justin", "Keith", "Kevin", "Leonard", "Liam", "Lucas", "Luke", "Matt", "Max", "Michael", "Nathan", "Neil", "Nicholas", "Oliver", "Owen", "Paul", "Peter", "Phil", "Piers", "Richard", "Robert", "Ryan", "Sam", "Sean", "Sebastian", "Simon", "Stephen", "Steven", "Stewart", "Thomas", "Tim", "Trevor", "Victor", "Warren", "William")
  private val SECOND_NAMES = Array("Abraham", "Allan", "Alsop", "Anderson", "Arnold", "Avery", "Bailey", "Baker", "Ball", "Bell", "Berry", "Black", "Blake", "Bond", "Bower", "Brown", "Buckland", "Burgess", "Butler", "Cameron", "Campbell", "Carr", "Chapman", "Churchill", "Clark", "Clarkson", "Coleman", "Cornish", "Davidson", "Davies", "Dickens", "Dowd", "Duncan", "Dyer", "Edmunds", "Ellison", "Ferguson", "Fisher", "Forsyth", "Fraser", "Gibson", "Gill", "Glover", "Graham", "Grant", "Gray", "Greene", "Hamilton", "Hardacre", "Harris", "Hart", "Hemmings", "Henderson", "Hill", "Hodges", "Howard", "Hudson", "Hughes", "Hunter", "Ince", "Jackson", "James", "Johnston", "Jones", "Kelly", "Kerr", "King", "Knox", "Lambert", "Langdon", "Lawrence", "Lee", "Lewis", "Lyman", "MacDonald", "Mackay", "Mackenzie", "MacLeod", "Manning", "Marshall", "Martin", "Mathis", "May", "McDonald", "McLean", "McGrath", "Metcalfe", "Miller", "Mills", "Mitchell", "Morgan", "Morrison", "Murray", "Nash", "Newman", "Nolan", "North", "Ogden", "Oliver", "Paige", "Parr", "Parsons", "Paterson", "Payne", "Peake", "Peters", "Piper", "Poole", "Powell", "Pullman", "Quinn", "Rampling", "Randall", "Rees", "Reid", "Roberts", "Robertson", "Ross", "Russell", "Rutherford", "Sanderson", "Scott", "Sharp", "Short", "Simpson", "Skinner", "Slater", "Smith", "Springer", "Stewart", "Sutherland", "Taylor", "Terry", "Thomson", "Tucker", "Turner", "Underwood", "Vance", "Vaughan", "Walker", "Wallace", "Walsh", "Watson", "Welch", "White", "Wilkins", "Wilson", "Wright", "Young")
  private val MOVIE_FIRST_WORD = Array("Robot", "Invader", "Defenders", "Cyborgs", "Agent", "Doctors", "Enemies", "Statues", "Ambush", "Ruins", "Martians")
  private val MOVIE_SECOND_WORD = Array("Our Culture", "Outer Space", "The Moon", "Mars", "Space", "The Stars", "The Dead", "Time")

  private val rand = new Random

  def generateName(): String =
    FIRST_NAMES(rand.nextInt(FIRST_NAMES.length)) + " " + SECOND_NAMES(rand.nextInt(SECOND_NAMES.length))

  def generateMovieTitle(): String = {
    var title = rand.nextInt(3) match {
      case 0 => MOVIE_FIRST_WORD(rand.nextInt(MOVIE_FIRST_WORD.length)) + " Of " + MOVIE_SECOND_WORD(rand.nextInt(MOVIE_SECOND_WORD.length))
      case 1 => MOVIE_FIRST_WORD(rand.nextInt(MOVIE_FIRST_WORD.length))
      case 2 => MOVIE_SECOND_WORD(rand.nextInt(MOVIE_SECOND_WORD.length))
    }
    if (rand.nextInt(5) == 0) {
      title += " 2"
    } else if (rand.nextInt(10) == 0) {
      title += " 3"
    }
    title
  }

  def toEmail(name: String, domain: String): String =
    name.replaceAll(" ", "") + "@" + domain

  def randomDate(from: LocalDate, to: LocalDate): LocalDate =
    from.plusDays(rand.nextInt(DAYS.between(from, to).toInt))

  def randomAlpha(count: Int): String =
    (1 to count).map(_ => ALPHA_NUMERIC(rand.nextInt(ALPHA_NUMERIC.length))).mkString

  def randomInt(from: Int, to: Int): Int =
    from + rand.nextInt((to - from) + 1)
}
