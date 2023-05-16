package com.believe.sr.tools

import com.believe.sr.constants.Fields
import org.apache.spark.sql.{DataFrame, SaveMode}
import zio.Task
import org.apache.spark.sql.types.StructType

import java.io.File
import java.nio.file.Path

object DataTools {

  def load(path: String, selectField: StructType): Task[DataFrame] = {
    for {
      ss <- SparkZio.ss
    } yield ss.read.format("csv")
      .option("header", "true").option("delimiter", ";")
      .schema(selectField)
      .load(path)
  }

  def writeToDeltaFormat(dataFrame: DataFrame,path: String) ={

    for {
        _ <- Task(dataFrame.write.mode(SaveMode.Overwrite).format("delta").save(getPath()))
    } yield ()
  }

  def getPath(): String = {
    val file = new File("src/main/resources/Output")
    val absolutePath = file.getAbsolutePath()
    absolutePath
  }
}
