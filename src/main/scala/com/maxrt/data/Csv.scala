package com.maxrt.data

import scala.collection.mutable.{ArrayBuffer, Map as MutMap}
import scala.reflect.ClassTag

object Csv {
  type Record = Array[String]
  type Data = Array[Record]

  def toText(data: Data): String = {
    var result = ""

    for (record <- data) {
      for (i <- record.indices) {
        result += record(i)
        if (i + 1 < record.length) {
          result += ","
        }
      }
      result += "\n"
    }

    result
  }

  def parseText(text: String, delimiter: String = ","): Data = {
    val result = ArrayBuffer.empty[Array[String]]

    text.lines().forEach(line => result.append(line.split(delimiter)))

    result.toArray
  }

  def parse[T: ClassTag](data: Data, creator: Record => T): Array[T] = {
    val result = ArrayBuffer.empty[T]

    for (record <- data) {
      result.append(creator(record))
    }

    result.toArray
  }

}
