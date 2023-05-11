package com.maxrt.data

import scala.collection.mutable.{ArrayBuffer, Map as MutMap}
import scala.util.Random
import java.time.LocalDate

object CsvGenerator {
  private type RecordData = Array[Array[String]]
  private type FileData = Map[String, String]

  private val PLACEHOLDER_TEXT = "Lorem ipsum dolor sit amet"

  private val rand = new Random

  private def generateActors(count: Int): RecordData = {
    val result = ArrayBuffer.empty[Array[String]]

    for (i <- 1 to count) {
      val record = ArrayBuffer.empty[String]

      record.append(i.toString)
      record.append(RandomDataGenerator.generateName())
      record.append(PLACEHOLDER_TEXT)
      record.append(RandomDataGenerator.randomDate(
        LocalDate.of(1970, 1, 1),
        LocalDate.of(2015, 1, 1)
      ).toString)

      result.append(record.toArray)
    }

    result.toArray
  }

  private def generateUsers(count: Int): RecordData = {
    val result = ArrayBuffer.empty[Array[String]]

    for (i <- 1 to count) {
      val record = ArrayBuffer.empty[String]
      val name = RandomDataGenerator.generateName()

      record.append(i.toString)
      record.append(name)
      record.append(RandomDataGenerator.toEmail(name, "gmail.com"))
      record.append(RandomDataGenerator.randomAlpha(8))

      result.append(record.toArray)
    }

    result.toArray
  }

  private def generateFilms(count: Int): RecordData = {
    val result = ArrayBuffer.empty[Array[String]]

    for (i <- 1 to count) {
      val record = ArrayBuffer.empty[String]

      record.append(i.toString)
      record.append(RandomDataGenerator.generateMovieTitle())
      record.append(PLACEHOLDER_TEXT)
      record.append(RandomDataGenerator.generateName())
      record.append((rand.nextInt(10).toFloat + (rand.nextInt(100).toFloat / 100.0)).toString)
      record.append(rand.nextInt(count).toString)
      record.append(RandomDataGenerator.randomInt(10000, 900000000).toString)

      result.append(record.toArray)
    }

    result.toArray
  }

  private def generateReviews(count: Int): RecordData = {
    val result = ArrayBuffer.empty[Array[String]]

    for (i <- 1 to count) {
      val record = ArrayBuffer.empty[String]

      record.append(i.toString)
      record.append(RandomDataGenerator.randomInt(1, count).toString)
      record.append(RandomDataGenerator.randomInt(1, count).toString)
      record.append(PLACEHOLDER_TEXT)
      record.append(PLACEHOLDER_TEXT)

      result.append(record.toArray)
    }

    result.toArray
  }

  private def generateActorFilms(count: Int): RecordData = {
    val result = ArrayBuffer.empty[Array[String]]

    for (i <- 1 to count) {
      val record = ArrayBuffer.empty[String]

      record.append(i.toString)
      record.append(RandomDataGenerator.randomInt(1, count).toString)
      record.append(RandomDataGenerator.randomInt(1, count).toString)

      result.append(record.toArray)
    }

    result.toArray
  }

  def generate(count: Int): FileData = {
    val result = MutMap.empty[String, String]

    result.update("actors.csv", Csv.toText(generateActors(count)))
    result.update("users.csv", Csv.toText(generateUsers(count)))
    result.update("films.csv", Csv.toText(generateFilms(count)))
    result.update("reviews.csv", Csv.toText(generateReviews(count)))
    result.update("actor_films.csv", Csv.toText(generateActorFilms(count)))

    result.toMap
  }
}
