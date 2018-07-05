package com.shah.checkout.api.internal

import com.shah.checkout.data.{Apple, Orange}

class SimpleOffersAuditorSpec extends AuditorSpec {

  override val auditor: Auditor = SimpleOffersAuditor
  behavior of "SimpleOffersAuditor"

  it should "price an empty list correctly" in {
    priceEmptyListCorrectly
  }

  it should "price a single Apple correctly" in {
    priceSingleItemCorrectly(Apple, 0.60)
  }

  it should "price a single Orange correctly" in {
    priceSingleItemCorrectly(Orange, 0.25)
  }

  it should "price a Multiple Apples correctly" in {
    priceMultipleItemsCorrectly(6, Apple)(1.80)
  }

  it should "price a Multiple Oranges correctly" in {
    priceMultipleItemsCorrectly(6, Orange)(1.00)
  }

  it should "price a mixture of items the same regardless of order" in {
    priceItemsTheSameRegardlessOfOrder
  }

  it should "price a mixture of items the same if bought over multiple carts" in {
    priceItemsTheSameIfBoughtInMultipleCarts
  }

  it should "price a mixture of Apple and Oranges correctly" in {
    auditor.getPrice(List(Apple, Apple, Orange, Apple)) shouldBe 1.45
  }
}

