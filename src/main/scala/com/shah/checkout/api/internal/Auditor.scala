package com.shah.checkout

import com.shah.checkout.data.KnownItem

trait Auditor {
  val priceItems: Seq[KnownItem] => Double
}

object SimpleAuditor extends Auditor {
  override val priceItems: Seq[KnownItem] => Double = _.map(_.cost).sum
}