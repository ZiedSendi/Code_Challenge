package com.believe.sr.tools

import org.apache.spark.sql.Row

import scala.util.Try

object ConvertTools {

  trait ConvertRowTo[T] {
    def getAsOption(row: Row, key: String): Option[T]
  }

  implicit class RowConvert(row: Row) {
    def getAsOption[T: ConvertRowTo](key: String): Option[T] =
      ConvertRowTo[T].getAsOption(row, key)
  }

  object ConvertRowTo {

    def apply[T](implicit convert: ConvertRowTo[T]): ConvertRowTo[T] = convert

    def convertTo[T](row: Row, key: String)(implicit convert: ConvertRowTo[T]): Option[T] =
      convert.getAsOption(row, key)

    implicit val convertRowToString: ConvertRowTo[String] = new ConvertRowTo[String] {
      override def getAsOption(row: Row, key: String): Option[String] =
        Try {
          row.getAs[Any](key).toString
        }.toOption.flatMap(Option.apply)
    }

    implicit val convertRowToBigInt: ConvertRowTo[BigInt] =
      new ConvertRowTo[BigInt] {
        override def getAsOption(row: Row, key: String): Option[BigInt] =
          convertRowToString.getAsOption(row, key).map(BigInt(_))
      }

    implicit val convertRowToDouble: ConvertRowTo[Double] =
      new ConvertRowTo[Double] {
        override def getAsOption(row: Row, key: String): Option[Double] =
          convertRowToString.getAsOption(row, key).filter(_.matches("\"^\\d+$\""))map(_.toDouble)
      }
  }
}
