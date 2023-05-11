package com.maxrt.repository

import com.maxrt.model.ActorFilm
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
trait ActorFilmRepository extends JpaRepository[ActorFilm, Int] {
  @Modifying
  @Transactional
  @Query(
    value = "truncate table actor_film cascade",
    nativeQuery = true
  )
  def truncate(): Unit
}
