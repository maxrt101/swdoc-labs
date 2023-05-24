package com.maxrt

import java.io.{FileReader, FileInputStream}
import java.util.{Properties, StringJoiner}
import org.json.simple.{JSONArray, JSONObject}
import com.maxrt.data.UrlJsonDataRetriever
import com.maxrt.output.{ConsoleOutputStrategy, OutputStrategy, RedisOutputStrategy}

object App {
  val URL = "https://data.sfgov.org/resource/yitu-d5am.json?$query=SELECT%0A%20%20%60title%60%2C%0A%20%20%60release_year%60%2C%0A%20%20%60locations%60%2C%0A%20%20%60fun_facts%60%2C%0A%20%20%60production_company%60%2C%0A%20%20%60distributor%60%2C%0A%20%20%60director%60%2C%0A%20%20%60writer%60%2C%0A%20%20%60actor_1%60%2C%0A%20%20%60actor_2%60%2C%0A%20%20%60actor_3%60%0AORDER%20BY%20%60title%60%20ASC%20NULL%20LAST%0ALIMIT%20100%0AOFFSET%200&"
  val PROP_KEY = "strategy"

  private def checkArg(args: Array[String], params: Array[String]): Boolean = {
    for (param <- params) {
      if (args.contains(param)) {
        return true
      }
    }
    false
  }

  private def getArg(args: Array[String], params: Array[String]): Option[String] = {
    for (param <- params) {
      if (args.contains(param)) {
        val idx = args.indexOf(param)
        if (args.length >= idx + 1) {
          return Option(args(idx + 1))
        }
      }
    }
    Option.empty[String]
  }

  private def getApplicationProperties: Properties = {
    val inputStream = getClass.getResourceAsStream("/application.properties")
    val properties = new Properties()
    properties.load(inputStream)
    properties
  }

  private def help(): Unit = {
    println("Usage: swdoclab2.jar [OPTIONS]")
    println("Options:")
    println("  -h, --help     - Shows this message")
    println("  -c, --console  - Select Console Output Strategy")
    println("  -r, --redis    - Select Console Output Strategy")
    println("  --config FILE  - Read from config file")
  }

  def main(args: Array[String]): Unit = {
    println("[+] SwDocLab4 v1.0")

    if (checkArg(args, Array("-h", "--help"))) {
      help()
      return
    }

    val applicationProperties = getApplicationProperties

    var outputStrategy: OutputStrategy = null

    if (checkArg(args, Array("--config"))) {
      getArg(args, Array("--config")) match {
        case Some(configFile) =>
          val props = new Properties
          props.load(new FileReader(configFile))
          outputStrategy = props.get(PROP_KEY) match {
            case "console" => new ConsoleOutputStrategy
            case "redis" => new RedisOutputStrategy(
              applicationProperties.getProperty("redis.host"),
              Integer.parseInt(applicationProperties.getProperty("redis.port")),
              applicationProperties.getProperty("redis.channel")
            )
            case strategy: String =>
              println(s"Error: Unknown strategy '$strategy'")
              return
          }
        case None =>
          help()
          return
      }
    }

    if (checkArg(args, Array("-c", "--console"))) {
      outputStrategy = new ConsoleOutputStrategy
    }

    if (checkArg(args, Array("-r", "--redis"))) {
      outputStrategy = new RedisOutputStrategy(
        applicationProperties.getProperty("redis.host"),
        Integer.parseInt(applicationProperties.getProperty("redis.port")),
        applicationProperties.getProperty("redis.channel")
      )
    }

    if (outputStrategy == null) {
      help()
      return
    }

    val data = UrlJsonDataRetriever.getData(URL)

    data.forEach(obj => {
      val json = obj.asInstanceOf[JSONObject]
      val keys = json.keySet().toArray.sortWith((a, b) => a.asInstanceOf[String] < b.asInstanceOf[String])
      val joiner = new StringJoiner(", ", "{", "}")

      for (key <- keys) {
        joiner.add(s"\"$key\": \"${json.get(key)}\"")
      }

      outputStrategy.output(joiner.toString)
    })
  }
}
