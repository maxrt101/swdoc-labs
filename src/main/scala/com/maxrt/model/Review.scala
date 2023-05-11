package com.maxrt.model

import javax.persistence.{Table, Column, Entity, Id, GeneratedValue, GenerationType}
import com.maxrt.data.Model

@Entity
@Table(name = "review")
class Review extends Model {
  @Id
  var id: Int = 0
  @Column(name = "user_id")
  var userId: Int = 0
  @Column(name = "film_id")
  var filmId: Int = 0
  var title: String = ""
  var text: String = ""

  override def toString: String = s"Review($id, $userId, $filmId, $title, $text)"
}
