

import org.scalatest.FunSuite

import org.scalatest.matchers.must.Matchers


import java.io.File
//todo Unit test with Zio-test lib
class TestInput extends FunSuite with Matchers {


  test ("Load albums data") {
    val  file = new File("src/resources/Output");
    val  absolutePath = file.getAbsolutePath();
    print(absolutePath)
  }


}