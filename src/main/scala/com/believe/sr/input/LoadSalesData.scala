package com.believe.sr.input
import com.believe.sr.constants.{Fields, Schema}
import com.believe.sr.input.LoadAlbumData.getClass
import com.believe.sr.tools.DataTools
import com.believe.sr.tools.DataTools.getClass
import org.apache.spark.sql.DataFrame
import zio.{UIO, ZIO}

object LoadSalesData {

  val  filePath = getClass.getResource(Fields.input_sales).getPath
   val readSalesFromResource: ZIO[Any, Throwable, DataFrame] = {
    for {
      _ <- UIO(println("------------------------------------------------------------------------------------"))
      _ <- UIO(println(s"[Load Album data]--Starting load of album data ${Fields.input_sales}-----------------------------"))
      _ <- UIO(println("------------------------------------------------------------------------------------"))
      df <- DataTools.load(
        filePath
      , Schema.salesSchema)
    } yield df
  }
}
