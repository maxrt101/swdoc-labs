package com.maxrt

import com.maxrt.data.CsvGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.ComponentScan
import java.io.{FileWriter, File}

@ComponentScan(Array("com.maxrt.controller", "com.maxrt.service", "com.maxrt.repository", "com.maxrt.data", "com.maxrt.auth"))
@SpringBootApplication
class App

object App {

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

  def main(args: Array[String]): Unit = {
    println("[+] SwDocLab2 v1.0")

    if (checkArg(args, Array("-h", "--help"))) {
      println("Usage: swdoclab2.jar [OPTIONS]")
      println("Options:")
      println("  -h, --help         - Shows this message")
      println("  -g, --generate-csv - Generate CSV files with random data")
      println("  -c, --count COUNT  - Count of data records for CSV files")
      println("  -o, --out FOLDER   - Output folder for CSV files")
      println("NOTE: If no options are provided - spring boot is started")
      return
    }

    if (checkArg(args, Array("-g", "--generate-csv"))) {
      var outFolder = "./"
      var count = "1000"

      getArg(args, Array("-o", "--out")) match {
        case Some(value) =>
          outFolder = value
          if (outFolder.last != '/') {
            outFolder += "/"
          }
        case None =>
      }

      getArg(args, Array("-c", "--count")) match {
        case Some(value) =>
          count = value
        case None =>
      }

      println(s"[+] Generating $count lines of CSV data into $outFolder")

      val csvs = CsvGenerator.generate(count.toInt)

      for ((file, text) <- csvs) {
        val writer = new FileWriter(new File(outFolder + file))
        writer.write(text)
        writer.close()
      }

      return
    }

    println("[+] Starting Spring Boot...")
    SpringApplication.run(classOf[App])
  }
}
