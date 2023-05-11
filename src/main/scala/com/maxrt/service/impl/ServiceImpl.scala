package com.maxrt.service.impl

import com.maxrt.data.{Service, Model}
import scala.jdk.OptionConverters._
import scala.jdk.CollectionConverters._

abstract class ServiceImpl[T <: Model] extends Service[T] {
  def get(id: Int): Option[T] = repository.findById(id).toScala
  def getAll(): List[T] = repository.findAll().asScala.toList
  def update(value: T): T = repository.save(value)
  def create(value: T): T = repository.save(value)
  def createAll(values: Iterable[T]): Unit = repository.saveAll(values.asJava)
  def delete(id: Int): Unit = repository.deleteById(id)
}
