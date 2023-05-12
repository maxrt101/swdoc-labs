package com.maxrt.model

import javax.persistence.{Table, Column, Entity, Id, GeneratedValue, GenerationType}
import com.maxrt.data.Model

@Entity
@Table(name = "film")
class Film extends Model {
  @Id
  var id: Int = 0
  var name: String = ""
  var description: String = ""
  var director: String = ""
  var rating: Float = 0
  @Column(name = "users_rated")
  var usersRated: Int = 0
  @Column(name = "box_office")
  var boxOffice: Int = 0

  def setId(id: Int): Unit = this.id = id
  def setName(name: String): Unit = this.name = name
  def setDescription(description: String): Unit = this.description = description
  def setDirector(director: String): Unit = this.director = director
  def setRating(rating: Float): Unit = this.rating = rating
  def setUsersRated(usersRated: Int): Unit = this.usersRated = usersRated
  def setBoxOffice(boxOffice: Int): Unit = this.boxOffice = boxOffice

  def addRating(rating: Int): Unit = {
    this.usersRated += 1
    this.rating = (this.rating + rating) / usersRated
  }

  override def toString: String = s"Film($id, $name, $description, $director, $rating, $usersRated, $boxOffice)"
}
