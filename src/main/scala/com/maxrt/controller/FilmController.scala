package com.maxrt.controller

import com.maxrt.model.Film
import com.maxrt.dto.FilmDto
import com.maxrt.service.FilmService

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/entity/film"))
class FilmController {
  @Autowired
  val filmService: FilmService = null

  @GetMapping(path = Array("/{id}"))
  def getFilm(@PathVariable(name = "id") id: Int): ResponseEntity[FilmDto] =
    filmService.get(id) match {
      case Some(actor) => new ResponseEntity(new FilmDto(actor), HttpStatus.OK)
      case None        => new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping
  def getAllFilms: ResponseEntity[java.util.List[FilmDto]] =
    new ResponseEntity(filmService.getAll().map(new FilmDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createFilm(@RequestBody actor: Film): ResponseEntity[Film] =
    new ResponseEntity(filmService.create(actor), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateFilm(@PathVariable("id") id: Int, @RequestBody film: Film): ResponseEntity[FilmDto] =
    filmService.get(id) match {
      case Some(_) =>
        film.id = id
        new ResponseEntity(new FilmDto(filmService.update(film)), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @DeleteMapping(path = Array("/{id}"))
  def deleteFilm(@PathVariable("id") id: Int): ResponseEntity[FilmDto] =
    filmService.get(id) match {
      case Some(film) =>
        filmService.delete(id)
        new ResponseEntity(new FilmDto(film), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
