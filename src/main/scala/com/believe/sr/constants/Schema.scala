package com.believe.sr.constants

import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object Schema {

  val albumSchema : StructType = new StructType()
     .add("upc",IntegerType,true)
     .add("album_name",StringType,true)
     .add("label_name",StringType,true)
     .add("country",StringType,true)

  val salesSchema : StructType = new StructType()
     .add("PRODUCT_UPC"     , StringType,true)
     .add("TRACK_ISRC_CODE" , StringType ,true)
     .add("TRACK_ID"        , StringType,true)
     .add("DELIVERY"        , StringType,true)
     .add("NET_TOTAL"       , StringType,true)
     .add("TERRITORY"       , StringType,false)

  val songsSchema : StructType = new StructType()
    .add("isrc"         ,StringType  ,true)
    .add("song_id"      ,IntegerType ,true)
    .add("song_name"    ,StringType  ,true)
    .add("artist_name"  ,StringType  ,true)
    .add("content_type" ,StringType  ,true)


}
