package com.maxrt.dto

import com.maxrt.model.Actor
import com.maxrt.data.Dto
import java.util.Date

class ActorDto(actor: Actor) extends Dto {
  val getId: Int = actor.id
  val getName: String = actor.name
  var getInfo: String = actor.info
  var getBirthDate: Date = actor.birthDate
}
