package com.maxrt.model

import java.util.Date
import javax.persistence.{Table, Column, Entity, Id, GeneratedValue, GenerationType}
import com.maxrt.data.Model

@Entity
@Table(name = "actor")
class Actor extends Model {
  @Id
  var id: Int = 0
  var name: String = ""
  var info: String = ""
  @Column(name = "birth_date")
  var birthDate: Date = new Date

  override def toString: String = s"Actor($id, $name, $birthDate, $info)"
}
