package com.shah.checkout.api.internal

import com.shah.checkout.data._

trait Scanner {
  protected val scanItems: Seq[BarCode] => Seq[Item]
  protected val validateItems: Seq[Item] => Seq[RecognisedItem]

  lazy val scanCodes: Seq[BarCode] => Seq[RecognisedItem] = {
    scanItems andThen validateItems
  }
}

object SimpleScanner extends Scanner {
  override protected val scanItems: Seq[BarCode] => Seq[Item] = _.map {
    case Orange.`barCode` => Orange
    case Apple.`barCode` => Apple
    case _ => UnrecognisedItem //trigger: "please wait for assistance/help is coming!" :D
  }

  override protected val validateItems: Seq[Item] => Seq[RecognisedItem] = {
    _.collect{
      case item:RecognisedItem => item
    }
  }
}