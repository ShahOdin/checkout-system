package com.shah.checkout.api.internal

import com.shah.checkout.data.{Cost, RecognisedItem}

trait Auditor {
  protected val sumUpPrices: Seq[RecognisedItem] => Cost

  private val roundUpToNearestPence: Cost => Cost = {
    BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  lazy val getPrice: Seq[RecognisedItem] => Cost = {
    sumUpPrices andThen roundUpToNearestPence
  }
}

object SimpleAuditor extends Auditor {
  override val sumUpPrices: Seq[RecognisedItem] => Cost = _.map(_.cost).sum
}

trait SimpleOffers {

  private type ItemCount = (RecognisedItem, Int)
  private type ItemCountMap = Map[RecognisedItem, Int]

  val countItems: Seq[RecognisedItem] => ItemCountMap = {
    _.groupBy(identity).mapValues(_.size)
  }

  import com.shah.checkout.data.{Apple, Orange}
  lazy val applyOffers: ItemCountMap => ItemCountMap = _.map {
    getOneFreeForEvery(Orange, 3) orElse getOneFreeForEvery(Apple,2) orElse normalPrice
  } //todo: parameterise this to fold on PartialFunction[ItemCount, ItemCount]s so OfferOps would be free from implementation details

  private def getOneFreeForEvery(item: RecognisedItem, amount: Int) : PartialFunction[ItemCount, ItemCount] = {
    case (`item`, count) =>
      (item, count - count / amount)
  }

  private val normalPrice: PartialFunction[ItemCount, ItemCount] = {
    case (item, count) => (item, count)
  }

  val priceItemCountSet: ItemCountMap => Cost = _.map {
    case (item, count) => item.cost * count
  }.sum
}

object SimpleOffersAuditor extends Auditor with SimpleOffers {
  override val sumUpPrices: Seq[RecognisedItem] => Cost = {
    countItems andThen applyOffers andThen priceItemCountSet
  }
}