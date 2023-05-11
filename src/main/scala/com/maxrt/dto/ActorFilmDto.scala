package com.maxrt.dto

import com.maxrt.model.ActorFilm
import com.maxrt.data.Dto

class ActorFilmDto(actorFilm: ActorFilm) extends Dto {
  def getId: Int = actorFilm.id
  def getActorId: Int = actorFilm.actorId
  def getFilmId: Int = actorFilm.filmId
}
