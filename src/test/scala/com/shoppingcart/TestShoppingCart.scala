package com.shoppingcart

import org.scalatest.{BeforeAndAfter, FlatSpec}

class TestShoppingCart extends FlatSpec with BeforeAndAfter {

  val applePrice: BigDecimal = 0.6
  val orangePrice: BigDecimal = 0.25

  before{
    ShoppingCart.orangeCount.set(0)
    ShoppingCart.appleCount.set(0)
  }
  
  "A collection of apples" should "have actual amount 6.0 for 10 apples." in {
    val apples = List("apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple")
    val totalBill = ShoppingCart.getTotalBill(apples, applePrice, 0)

    assert(totalBill === 6.0)
  }

  "A collection of oranges" should "return actual amount 2.5 for 10 oranges." in {
    val apples = List("orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange")
    val totalBill = ShoppingCart.getTotalBill(apples, 0, orangePrice)

    assert(totalBill === 2.5)
  }

  "A collection of oranges and apples" should "return total amount 8.5 for 10 apples and 10 oranges." in {
    val fruits = List("apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple",
      "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange", "orange")
    val totalBill = ShoppingCart.getTotalBill(fruits, applePrice, orangePrice)

    assert(totalBill === 8.5)
  }

  "Inventory file" should "return 13 fruits." in
    assert(ShoppingCart.getInventoryFile().size === 13)
}
