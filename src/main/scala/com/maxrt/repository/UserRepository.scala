package com.maxrt.repository

import com.maxrt.model.User
import scala.jdk.CollectionConverters._
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.{JpaRepository, Modifying, Query}
import org.springframework.transaction.annotation.Transactional

@Repository
trait UserRepository extends JpaRepository[User, Int] {
  @Modifying
  @Transactional
  @Query(
    value = "truncate table users cascade",
    nativeQuery = true
  )
  def truncate(): Unit

  def findByName(name: String): Option[User] = {
    val found = findAll.toArray().filter(u => u.asInstanceOf[User].name == name)
    if (found.isEmpty)
      Option.empty
    else
      Option(found.toList.head.asInstanceOf[User])
  }
}
