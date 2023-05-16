package com.believe.sr.tools

import org.apache.spark.sql.SparkSession
import zio.Task

object SparkZio {
  val ss : Task[SparkSession] = Task(SparkSession.builder().appName("IngestProcess").master("local[*]")
    .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
    .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
    .getOrCreate()
  )
}
