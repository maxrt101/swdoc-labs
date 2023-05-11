package com.maxrt.service

import com.maxrt.model.Review
import com.maxrt.repository.ReviewRepository
import com.maxrt.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ReviewService extends ServiceImpl[Review] {
  @Autowired
  override val repository: ReviewRepository = null

  def truncate(): Unit = repository.truncate()
}
