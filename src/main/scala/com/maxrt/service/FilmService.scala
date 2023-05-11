package com.maxrt.service

import com.maxrt.model.Film
import com.maxrt.repository.FilmRepository
import com.maxrt.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class FilmService extends ServiceImpl[Film] {
  @Autowired
  override val repository: FilmRepository = null

  def truncate(): Unit = repository.truncate()
}
