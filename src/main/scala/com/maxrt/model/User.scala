package com.maxrt.model

import javax.persistence.{Table, Column, Entity, Id, GeneratedValue, GenerationType}
import com.maxrt.data.Model

@Entity
@Table(name = "users")
class User extends Model {
  @Id
  var id: Int = 0
  var name: String = ""
  var email: String = ""
  @Column(name = "password_hash")
  var passwordHash: String = ""

  def setId(id: Int): Unit = this.id = id
  def setName(name: String): Unit = this.name = name
  def setEmail(email: String): Unit = this.email = email
  def setPasswordHash(passwordHash: String): Unit = this.passwordHash = passwordHash

  override def toString: String = s"User($id, $name, $email, $passwordHash)"
}
