package com.maxrt.service

import com.maxrt.model.Actor
import com.maxrt.repository.ActorRepository
import com.maxrt.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ActorService extends ServiceImpl[Actor] {
  @Autowired
  override val repository: ActorRepository = null

  def truncate(): Unit = repository.truncate()
}
