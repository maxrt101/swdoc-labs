package com.maxrt.dto

import com.maxrt.model.Film
import com.maxrt.data.Dto

class FilmDto(film: Film) extends Dto {
  def getId: Int = film.id
  def getName: String = film.name
  def getDescription: String = film.description
  def getDirector: String = film.director
  def getRating: Float = film.rating
  def getUsersRated: Int = film.usersRated
  def getBoxOffice: Int = film.boxOffice
}
