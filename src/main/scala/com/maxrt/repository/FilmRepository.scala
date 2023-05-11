package com.maxrt.repository

import com.maxrt.model.Film
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
trait FilmRepository extends JpaRepository[Film, Int] {
  @Modifying
  @Transactional
  @Query(
    value = "truncate table film cascade",
    nativeQuery = true
  )
  def truncate(): Unit
}
