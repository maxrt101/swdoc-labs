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

  def setId(id: Int): Unit = this.id = id
  def setUserId(userId: Int): Unit = this.userId = userId
  def setFilmId(filmId: Int): Unit = this.filmId = filmId
  def setTitle(title: String): Unit = this.title = title
  def setText(text: String): Unit = this.text = text

  override def toString: String = s"Review($id, $userId, $filmId, $title, $text)"
}
