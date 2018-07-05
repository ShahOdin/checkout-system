package com.shah.checkout.api.internal

import com.shah.checkout.data.{Apple, Cost, RecognisedItem, Orange}
import org.scalatest.{FlatSpec, Matchers}

trait AuditorSpec extends FlatSpec with Matchers {

  val auditor: Auditor

  def priceEmptyListCorrectly = {
    auditor.getPrice(Nil) shouldBe 0
  }

  def priceSingleItemCorrectly(item:RecognisedItem, cost: Cost) = {
    auditor.getPrice(List(item)) shouldBe cost
  }


  def priceMultipleItemsCorrectly(count: Int, item: RecognisedItem)(cost: Cost) = {
    auditor.getPrice(List.fill(count)(item)) shouldBe cost
  }

  def priceItemsTheSameRegardlessOfOrder = {
    val price1 = auditor.getPrice(List(Apple, Apple, Orange, Apple))
    val price2 = auditor.getPrice(List(Apple, Apple, Apple, Orange))

    price1 shouldBe price2
  }

  def priceItemsTheSameIfBoughtInMultipleCarts = {
    val price1 = auditor.getPrice(List(Apple, Apple, Orange, Apple))

    val price2 = auditor.getPrice(List(Apple, Orange))
    val price3 = auditor.getPrice(List(Apple, Apple))

    price1 shouldBe price2 + price3
  }

}
