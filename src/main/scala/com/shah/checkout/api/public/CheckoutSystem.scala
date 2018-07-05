package com.shah.checkout.api.public

import com.shah.checkout.api.internal._
import com.shah.checkout.data.{BarCode, Cost}

trait CheckoutSystem {
  def checkout(codes: Seq[BarCode]): Cost
}

object CheckoutSystem {
  def apply[internal](auditor:Auditor, scanner: Scanner): CheckoutSystem
  = (scanner.scanCodes andThen auditor.getPrice)(_)

  val SimpleShoppingCart: CheckoutSystem = CheckoutSystem(SimpleAuditor, SimpleScanner)
  val ShoppingCartWithSimpleOffers: CheckoutSystem = CheckoutSystem(SimpleOffersAuditor, SimpleScanner)
}