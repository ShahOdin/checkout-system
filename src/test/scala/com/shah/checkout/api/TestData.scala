package com.shah.checkout.api

import com.shah.checkout.data.{Apple, BarCode, Orange}

object TestData {

  val mockedCodes: Seq[BarCode] = List(Orange.barCode, Orange.barCode, Orange.barCode, Apple.barCode)

}
