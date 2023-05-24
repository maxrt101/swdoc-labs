package com.maxrt.output

import redis.clients.jedis.Jedis

class RedisOutputStrategy(host: String, port: Int, channel: String) extends OutputStrategy {

  private val jedis = new Jedis(host, port)
  
  def output(obj: String): Unit = {
    jedis.rpush(channel, obj)
  }

  def close(): Unit = {
    jedis.close()
  }
}
