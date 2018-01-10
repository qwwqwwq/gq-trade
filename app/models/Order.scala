package models

trait Order {
  def getOrderType: Order.Type.Value
}

object Order {

  object Type extends Enumeration {
    type Type = Value
    val BUY, SELL = Value
  }
}

case class BuyOrder(buyingCoin: Coin,
                    buyAmount: Double,
                    sellingCoin: Coin,
                    sellAmount: Double,
                    active: Boolean) extends Order {
  override def getOrderType: Order.Type.Value = Order.Type.BUY
}

case class SellOrder(buyingCoin: Coin,
                     buyAmount: Double,
                     sellingCoin: Coin,
                     sellAmount: Double,
                     active: Boolean) extends Order {
  override def getOrderType: Order.Type.Value = Order.Type.SELL
}
