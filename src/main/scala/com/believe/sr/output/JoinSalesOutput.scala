package com.believe.sr.output

import com.believe.sr.constants.Fields
import org.apache.spark.sql.{DataFrame, SaveMode}
import zio.ZIO
import com.believe.sr.input.{LoadAlbumData, LoadSalesData, LoadSongsData}
import com.believe.sr.models.ModelOutput
import zio.{Task, UIO}
import com.believe.sr.tools.ConvertTools._
import com.believe.sr.tools.DataTools



object JoinSalesOutput {
   val processTasks: ZIO[Any, Throwable, DataFrame] = {
    for {
      _ <- UIO(println("----------- Start loading input data------------"))
      albums <- LoadAlbumData.readAlbumFromResource
      songs  <- LoadSongsData.readSongsFromResource
      sales  <- LoadSalesData.readSalesFromResource

      _ <- UIO(println("-----------Business processing data-------------"))

      outputResultDf <- Task(sales.join(songs,
        sales("TRACK_ISRC_CODE") <=> songs("isrc") &&
          sales("TRACK_ID") <=> songs("song_id"), "left")
        .join(albums,
          sales("PRODUCT_UPC") <=> albums("upc") &&
            sales("TERRITORY") <=> albums("country"), "left")
        .where(sales("PRODUCT_UPC").isNotNull && sales("TERRITORY").isNotNull)
        .where(sales("TRACK_ISRC_CODE").isNotNull && sales("TRACK_ID").isNotNull)
      )

      output <- Task {
        import outputResultDf.sparkSession.implicits._
        outputResultDf.map {
          row =>
            ModelOutput(
              upc                = row.getAsOption[BigInt]("PRODUCT_UPC"),
              isrc               = row.getAsOption[String]("isrc"),
              label_name         = row.getAsOption[String]("label_name"),
              album_name         = row.getAsOption[String]("album_name"),
              song_id            = row.getAsOption[BigInt]("song_id"),
              song_name          = row.getAsOption[String]("song_name"),
              artist_name        = row.getAsOption[String]("artist_name"),
              content_type       = row.getAsOption[String]("content_type"),
              total_net_revenue  = row.getAsOption[Double]("NET_TOTAL"),
              sales_country      = row.getAsOption[String]("country"),
            )
        }.toDF()
      }
     path        <- Task(DataTools.getPath())
      preparedDf <- Task(output.na.drop(output.columns))
      _          <- DataTools.writeToDeltaFormat(preparedDf,path)

    } yield output
  }




}
