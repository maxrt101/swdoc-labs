package com.maxrt.controller

import com.maxrt.model.Actor
import com.maxrt.dto.ActorDto
import com.maxrt.service.ActorService

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/entity/actor"))
class ActorController {
  @Autowired
  val actorService: ActorService = null

  @GetMapping(path = Array("/{id}"))
  def getActor(@PathVariable(name = "id") id: Int): ResponseEntity[ActorDto] =
    actorService.get(id) match {
      case Some(actor) => new ResponseEntity(new ActorDto(actor), HttpStatus.OK)
      case None        => new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping
  def getAllActors: ResponseEntity[java.util.List[ActorDto]] =
    new ResponseEntity(actorService.getAll().map(new ActorDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createActor(@RequestBody actor: Actor): ResponseEntity[Actor] =
    new ResponseEntity(actorService.create(actor), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateActor(@PathVariable("id") id: Int, @RequestBody actor: Actor): ResponseEntity[ActorDto] =
    actorService.get(id) match {
      case Some(_) =>
        actor.id = id
        new ResponseEntity(new ActorDto(actorService.update(actor)), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @DeleteMapping(path = Array("/{id}"))
  def deleteActor(@PathVariable("id") id: Int): ResponseEntity[ActorDto] =
    actorService.get(id) match {
      case Some(actor) =>
        actorService.delete(id)
        new ResponseEntity(new ActorDto(actor), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
