package com.maxrt.controller

import com.maxrt.model.ActorFilm
import com.maxrt.dto.ActorFilmDto
import com.maxrt.service.ActorFilmService

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/entity/actor_film"))
class ActorFilmController {
  @Autowired
  val actorFilmService: ActorFilmService = null

  @GetMapping(path = Array("/{id}"))
  def getActorFilm(@PathVariable(name = "id") id: Int): ResponseEntity[ActorFilmDto] =
    actorFilmService.get(id) match {
      case Some(actorFilm) => new ResponseEntity(new ActorFilmDto(actorFilm), HttpStatus.OK)
      case None            => new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping
  def getAllActorFilms: ResponseEntity[java.util.List[ActorFilmDto]] =
    new ResponseEntity(actorFilmService.getAll().map(new ActorFilmDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createActorFilm(@RequestBody actorFilm: ActorFilm): ResponseEntity[ActorFilm] =
    new ResponseEntity(actorFilmService.create(actorFilm), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateActorFilm(@PathVariable("id") id: Int, @RequestBody actorFilm: ActorFilm): ResponseEntity[ActorFilmDto] =
    actorFilmService.get(id) match {
      case Some(_) =>
        actorFilm.id = id
        new ResponseEntity(new ActorFilmDto(actorFilmService.update(actorFilm)), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @DeleteMapping(path = Array("/{id}"))
  def deleteActorFilm(@PathVariable("id") id: Int): ResponseEntity[ActorFilmDto] =
    actorFilmService.get(id) match {
      case Some(actorFilm) =>
        actorFilmService.delete(id)
        new ResponseEntity(new ActorFilmDto(actorFilm), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping(path = Array("/remove_by_film/{id}"))
  def deleteByFilmId(@PathVariable("id") id: Int): ResponseEntity[String] = {
    for (actorFilm <- actorFilmService.getAll()) {
      if (actorFilm.filmId == id) {
        actorFilmService.delete(actorFilm.id)
      }
    }
    new ResponseEntity("", HttpStatus.OK)
  }
}

