package com.shah.checkout

package object data {

  sealed trait Item
  case object UnrecognisedItem extends Item

  import java.util.UUID

  type BarCode = UUID
  type Cost = Double //todo: is it worth it to abstract over this?

  sealed trait RecognisedItem extends Item {
    val cost: Cost
    val barCode: BarCode
  }

  case object Apple extends RecognisedItem {
    val cost: Cost = 0.60
    val barCode: BarCode = UUID.randomUUID()
  }

  case object Orange extends RecognisedItem {
    val cost: Cost = 0.25
    val barCode: BarCode = UUID.randomUUID()
  }

}
