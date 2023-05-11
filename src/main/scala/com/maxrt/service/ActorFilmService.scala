package com.maxrt.service

import com.maxrt.model.ActorFilm
import com.maxrt.repository.ActorFilmRepository
import com.maxrt.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ActorFilmService extends ServiceImpl[ActorFilm] {
  @Autowired
  override val repository: ActorFilmRepository = null
  
  def truncate(): Unit = repository.truncate()
}
