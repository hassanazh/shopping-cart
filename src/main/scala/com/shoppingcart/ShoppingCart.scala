package com.shoppingcart

import java.util.concurrent.atomic.AtomicInteger
import scala.io.Source

object ShoppingCart {
    val appleCount = new AtomicInteger(0)
    val orangeCount = new AtomicInteger(0)
  def main(args: Array[String]): Unit = {
    val appleAmount: BigDecimal = 0.6
    val orangeAmount: BigDecimal = 0.25

    val totalBill = getTotalBill(getInventoryFile(), appleAmount, orangeAmount)

    println(s"Your actual bill is: £ $totalBill")
    println(s"Your discounted bill is: £ ${getFinalBillAfterDiscount(totalBill, appleAmount, orangeAmount)}")
  }

  def getInventoryFile(fileName: String = "/inventory.txt"): List[String] =
    Source.fromInputStream(getClass.getResourceAsStream(fileName)).getLines().toList

  def getTotalBill(inventory: List[String], appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal =
    inventory.foldRight(0.0: BigDecimal) { (fruit, amount) =>
      fruit match {
        case "apple" | "Apple" =>
          appleCount.incrementAndGet()
          amount + appleAmount
        case "orange" | "Orange" =>
          orangeCount.incrementAndGet()
          amount + orangeAmount
        case _ => amount
      }
    }

  def getFinalBillAfterDiscount(totalBill: BigDecimal, appleAmount: BigDecimal, orangeAmount: BigDecimal): BigDecimal =
    totalBill -
      ((appleCount.get() / 2) * appleAmount) -
      ((orangeCount.get() / 3) * orangeAmount)
}
