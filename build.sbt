
import sbt.{Def,_}

version := "0.1.0-SNAPSHOT"
lazy val root = (project in file("."))
  .settings(name := "coding_challenge")
  .settings(extraSettings)

val extraSettings =
  Def.settings(
    organization :="com.believe.sr",
    scalaVersion := "2.13.8",
    libraryDependencies ++=Seq(
      "dev.zio"      %% "zio"   % "1.0.12",
      "dev.zio" %% "zio-interop-reactivestreams" % "1.3.2",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.scalacheck" %% "scalacheck" % "1.17.0",
      "org.scalatest" %% "scalatest" % "3.1.1",
      "org.apache.spark" %% "spark-sql" % "3.3.1",
      "log4j" % "log4j" % "1.2.17",
      "io.delta" %% "delta-core" % "2.2.0"
     // "dev.zio" %% "zio-test" % "2.0.13" exclude ( "dev.zio","zio_1.0.12")
    )
  )


