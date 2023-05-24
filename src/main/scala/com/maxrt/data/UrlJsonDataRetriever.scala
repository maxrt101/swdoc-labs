package com.maxrt.data

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import java.util.stream.Collectors

import org.json.simple.{JSONArray, JSONObject}
import org.json.simple.parser.JSONParser

object UrlJsonDataRetriever {
  def getData(urlString: String): JSONArray = {
    val url = new URL(urlString)
    val connection = url.openConnection().asInstanceOf[HttpURLConnection]
    connection.setRequestMethod("GET")

    val requestResult  = new BufferedReader(new InputStreamReader(connection.getInputStream)).lines().collect(Collectors.joining("\n"))

    (new JSONParser).parse(requestResult).asInstanceOf[JSONArray]
  }
}
