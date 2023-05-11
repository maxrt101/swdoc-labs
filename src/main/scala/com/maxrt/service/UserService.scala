package com.maxrt.service

import com.maxrt.model.User
import com.maxrt.repository.UserRepository
import com.maxrt.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class UserService extends ServiceImpl[User] {
  @Autowired
  override val repository: UserRepository = null

  def truncate(): Unit = repository.truncate()
  def findByName(name: String): Option[User] = repository.findByName(name)
}
