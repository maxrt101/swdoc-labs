package com.maxrt.controller

import com.maxrt.model._
import com.maxrt.service._
import com.maxrt.data.Csv

import java.util.Date
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import scala.collection.mutable.Map as MutMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

import java.time.LocalDate

@RestController
@RequestMapping(path = Array("/api/data"))
class DataController {
  @Autowired val actorService: ActorService = null
  @Autowired val actorFilmService: ActorFilmService = null
  @Autowired val filmService: FilmService = null
  @Autowired val reviewService: ReviewService = null
  @Autowired val userService: UserService = null

  private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  private var actorData = Array.empty[Actor]
  private var actorFilmData = Array.empty[ActorFilm]
  private var filmData = Array.empty[Film]
  private var reviewData = Array.empty[Review]
  private var userData = Array.empty[User]

  private def parseCsv(entity: String, text: String): Unit =
    entity match {
      case "actors" =>
        actorData = Csv.parse(Csv.parseText(text), (record) => {
          val actor = new Actor
          actor.id = record(0).toInt
          actor.name = record(1)
          actor.info = record(2)
          actor.birthDate = Date.from(LocalDate.parse(record(3), dateTimeFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant)
          actor
        })
      case "actor_films" =>
        actorFilmData = Csv.parse(Csv.parseText(text), (record) => {
          val actorFilm = new ActorFilm
          actorFilm.id = record(0).toInt
          actorFilm.actorId = record(1).toInt
          actorFilm.filmId = record(2).toInt
          actorFilm
        })
      case "films" =>
        filmData = Csv.parse(Csv.parseText(text), (record) => {
          val film = new Film
          film.id = record(0).toInt
          film.name = record(1)
          film.description = record(2)
          film.director = record(3)
          film.rating = record(4).toFloat
          film.usersRated = record(5).toInt
          film.boxOffice = record(6).toInt
          film
        })
      case "reviews" =>
        reviewData = Csv.parse(Csv.parseText(text), (record) => {
          val review = new Review
          review.id = record(0).toInt
          review.userId = record(1).toInt
          review.filmId = record(2).toInt
          review.title = record(3)
          review.text = record(4)
          review
        })
      case "users" =>
        userData = Csv.parse(Csv.parseText(text), (record) => {
          val user = new User
          user.id = record(0).toInt
          user.name = record(1)
          user.email = record(2)
          user.passwordHash = record(3)
          user
        })
    }

  @GetMapping(path = Array("/truncate"))
  def truncate(): ResponseEntity[String] = {
    actorFilmService.truncate()
    reviewService.truncate()
    filmService.truncate()
    actorService.truncate()
    userService.truncate()
    ResponseEntity.ok("")
  }

  @PostMapping(path = Array("/load/{entity}"))
  def loadCsv(@PathVariable(name = "entity") entity: String, @RequestBody text: String): ResponseEntity[String] = {
    parseCsv(entity, text)
    ResponseEntity.ok("")
  }

  @GetMapping(path = Array("/save"))
  def saveCsv(): ResponseEntity[String] = {
    userService.createAll(userData)
    actorService.createAll(actorData)
    filmService.createAll(filmData)
    reviewService.createAll(reviewData)
    actorFilmService.createAll(actorFilmData)
    ResponseEntity.ok("")
  }

  @GetMapping(path = Array("/clear"))
  def clearCsv(): ResponseEntity[String] = {
    userData = Array()
    actorData = Array()
    filmData = Array()
    reviewData = Array()
    actorFilmData = Array()
    ResponseEntity.ok("")
  }

}
