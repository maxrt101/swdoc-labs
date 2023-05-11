package com.maxrt.dto

import com.maxrt.model.User
import com.maxrt.data.Dto

class UserDto(user: User) extends Dto {
  def getId: Int = user.id
  def getName: String = user.name
  def getEmail: String = user.email
  def getPasswordHash: String = user.passwordHash
}
