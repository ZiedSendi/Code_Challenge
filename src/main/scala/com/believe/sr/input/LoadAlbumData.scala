package com.believe.sr.input

import com.believe.sr.constants.{Fields, Schema}
import com.believe.sr.tools.DataTools
import org.apache.spark.sql.DataFrame
import zio.{UIO, ZIO}

object LoadAlbumData {
  val  filePath = getClass.getResource(Fields.input_albums).getPath
   val readAlbumFromResource: ZIO[Any, Throwable, DataFrame] = {
    for {
   _  <-  UIO(println("------------------------------------------------------------------------------------"))
   _  <-  UIO(println(s"[Load Album data]--Starting loading album data from ${Fields.input_albums}-----------------------------"))
   _  <-  UIO(println("------------------------------------------------------------------------------------"))
   df <-  DataTools.load(filePath,Schema.albumSchema)
    } yield df
  }
}
