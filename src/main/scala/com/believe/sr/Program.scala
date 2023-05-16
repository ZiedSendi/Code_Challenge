package com.believe.sr

import com.believe.sr.output.JoinSalesOutput.processTasks
import zio.{App, ExitCode, UIO, URIO, ZEnv}

object Program extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    processTasks
      .foldM(
        error => UIO(println(s"Execution failed with error: $error")).as(ExitCode.failure),
        _     => UIO(println("Execution succeeded")).as(ExitCode.success)
      )
  }
}
