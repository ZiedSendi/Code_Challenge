package com.believe.sr.input
import com.believe.sr.constants.{Fields, Schema}
import com.believe.sr.input.LoadAlbumData.getClass
import com.believe.sr.tools.DataTools
import org.apache.spark.sql.DataFrame
import zio.{UIO, ZIO}

object LoadSongsData {

  val  filePath = getClass.getResource(Fields.input_songs).getPath
   val readSongsFromResource: ZIO[Any, Throwable, DataFrame] = {
    for {
      _ <- UIO(println("------------------------------------------------------------------------------------"))
      _ <- UIO(println(s"[Load Album data]--Starting load of album data ${Fields.input_songs}-----------------------------"))
      _ <- UIO(println("------------------------------------------------------------------------------------"))
      df <- DataTools.load(filePath, Schema.songsSchema)
    } yield df
  }
}
