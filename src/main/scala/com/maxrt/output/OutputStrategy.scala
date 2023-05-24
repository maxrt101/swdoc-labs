package com.maxrt.output

trait OutputStrategy {
  def output(obj: String): Unit
}
