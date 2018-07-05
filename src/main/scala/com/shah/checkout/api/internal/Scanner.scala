package com.shah.checkout

import com.shah.checkout.data._

trait Scanner {
  val scanItems: Seq[BarCode] => Seq[Item]
  val validateItems: Seq[Item] => Seq[KnownItem]
}

object SimpleScanner extends Scanner {
  val scanItems: Seq[BarCode] => Seq[Item] = _.map {
    case Orange.code => Orange
    case Apple.code => Apple
    case _ => UnknownItem //trigger: "please wait for assistance/help is coming!" :D
  }

  val validateItems: Seq[Item] => Seq[KnownItem] = {
    _.collect{
      case item:KnownItem => item
    }
  }
}