package com.maxrt.controller

import com.maxrt.model.User
import com.maxrt.dto.UserDto
import com.maxrt.service.UserService

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/entity/user"))
class UserController {
  @Autowired
  val userService: UserService = null

  @GetMapping(path = Array("/{id}"))
  def getFilm(@PathVariable(name = "id") id: Int): ResponseEntity[UserDto] =
    userService.get(id) match {
      case Some(actor) => new ResponseEntity(new UserDto(actor), HttpStatus.OK)
      case None        => new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping
  def getAllFilms: ResponseEntity[java.util.List[UserDto]] =
    new ResponseEntity(userService.getAll().map(new UserDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createFilm(@RequestBody user: User): ResponseEntity[User] =
    new ResponseEntity(userService.create(user), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateFilm(@PathVariable("id") id: Int, @RequestBody user: User): ResponseEntity[UserDto] =
    userService.get(id) match {
      case Some(_) =>
        user.id = id
        new ResponseEntity(new UserDto(userService.update(user)), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @DeleteMapping(path = Array("/{id}"))
  def deleteFilm(@PathVariable("id") id: Int): ResponseEntity[UserDto] =
    userService.get(id) match {
      case Some(film) =>
        userService.delete(id)
        new ResponseEntity(new UserDto(film), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
