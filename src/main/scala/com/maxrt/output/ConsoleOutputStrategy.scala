package com.maxrt.output

class ConsoleOutputStrategy extends OutputStrategy {
  def output(obj: String): Unit = {
    println(obj)
  }
}
