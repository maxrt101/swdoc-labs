package com.maxrt.model

import javax.persistence.{Table, Column, Entity, Id, GeneratedValue, GenerationType}
import com.maxrt.data.Model

@Entity
@Table(name = "actor_film")
class ActorFilm extends Model {
  @Id
  var id: Int = 0
  @Column(name = "actor_id")
  var actorId: Int = 0
  @Column(name = "film_id")
  var filmId: Int = 0

  override def toString: String = s"ActorFilm($id, $actorId, $filmId)"
}
