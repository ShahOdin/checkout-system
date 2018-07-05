package com.shah.checkout.api.internal

import java.util.UUID

import com.shah.checkout.api.TestData.mockedCodes
import com.shah.checkout.data.{Apple, Orange}
import org.scalatest.{FlatSpec, Matchers}

class SimpleScannerSpec extends FlatSpec with Matchers {

  behavior of "SimpleScanner"

  it should "scan the code for an Apple " in {
    SimpleScanner.scanCodes(List(Apple.barCode)) shouldBe List(Apple)
  }

  it should "scan the code for an Orange " in {
    SimpleScanner.scanCodes(List(Orange.barCode)) shouldBe List(Orange)
  }

  it should "keep count of the scanned items" in {
    val items = SimpleScanner.scanCodes(mockedCodes)
    items.collect { case i@Apple => i }.length shouldBe 1
    items.collect { case i@Orange => i }.length shouldBe 3
  }

  it should "ignore invalid codes" in {
    val items = SimpleScanner.scanCodes(mockedCodes :+ UUID.randomUUID)
    items.length shouldBe 4
  }


}
