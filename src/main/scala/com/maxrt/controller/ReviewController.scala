package com.maxrt.controller

import com.maxrt.model.Review
import com.maxrt.dto.ReviewDto
import com.maxrt.service.ReviewService

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/entity/review"))
class ReviewController {
  @Autowired
  val reviewService: ReviewService = null

  @GetMapping(path = Array("/{id}"))
  def getFilm(@PathVariable(name = "id") id: Int): ResponseEntity[ReviewDto] =
    reviewService.get(id) match {
      case Some(review) => new ResponseEntity(new ReviewDto(review), HttpStatus.OK)
      case None         => new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @GetMapping
  def getAllFilms: ResponseEntity[java.util.List[ReviewDto]] =
    new ResponseEntity(reviewService.getAll().map(new ReviewDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createFilm(@RequestBody review: Review): ResponseEntity[Review] =
    new ResponseEntity(reviewService.create(review), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateFilm(@PathVariable("id") id: Int, @RequestBody review: Review): ResponseEntity[ReviewDto] =
    reviewService.get(id) match {
      case Some(_) =>
        review.id = id
        new ResponseEntity(new ReviewDto(reviewService.update(review)), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }

  @DeleteMapping(path = Array("/{id}"))
  def deleteFilm(@PathVariable("id") id: Int): ResponseEntity[ReviewDto] =
    reviewService.get(id) match {
      case Some(review) =>
        reviewService.delete(id)
        new ResponseEntity(new ReviewDto(review), HttpStatus.OK)
      case None =>
        new ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
