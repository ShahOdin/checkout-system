package com.shah.checkout.api.public

import com.shah.checkout.api.TestData.mockedCodes
import org.scalatest.{FlatSpec, Matchers}

class ShoppingCartWithSimpleOffersSpec extends FlatSpec with Matchers {

  val checkoutSystem: CheckoutSystem = CheckoutSystem.ShoppingCartWithSimpleOffers

  behavior of "ShoppingCartWithSimpleOffers"

  it should "successfully check-out the mocked codes with the correct price." in {
    checkoutSystem.checkout(mockedCodes) shouldBe 1.10
  }

}
