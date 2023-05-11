package com.maxrt.repository

import com.maxrt.model.Review
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
trait ReviewRepository extends JpaRepository[Review, Int] {
  @Modifying
  @Transactional
  @Query(
    value = "truncate table review cascade",
    nativeQuery = true
  )
  def truncate(): Unit
}
