package com.maxrt.repository

import com.maxrt.model.Actor
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
trait ActorRepository extends JpaRepository[Actor, Int] {
  @Modifying
  @Transactional
  @Query(
    value = "truncate table actor cascade",
    nativeQuery = true
  )
  def truncate(): Unit
}
