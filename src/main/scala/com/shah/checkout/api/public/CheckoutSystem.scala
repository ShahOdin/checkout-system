package com.shah.checkout.api

import com.shah.checkout.{Auditor, Scanner}
import com.shah.checkout.data.BarCode

trait CheckoutSystem {
  def checkout(codes: Seq[BarCode]): Double
}

object CheckoutSystem {
  def apply(auditor:Auditor, scanner: Scanner): CheckoutSystem
  = (scanner.scanItems andThen scanner.validateItems andThen auditor.priceItems)(_)
}