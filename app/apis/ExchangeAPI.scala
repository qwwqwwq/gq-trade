package apis

import models._

import scala.concurrent.duration.FiniteDuration

trait ExchangeAPI {

  def getActiveOrders: Set[Order]

  def createOrder: Order

  def getOrder(orderId: String): Order

  def cancelOrder(orderId: String): Boolean

  def getBalances: Set[Balance]

  def getBalance: Balance

  def getAddress(coin: Coin): Address

  /**
   * Get rates for a certain coin.
   */
  def getRates(coin: Coin): Set[Rate]

  /**
   * Get rates for all coins.
   */
  def getRates: Set[Rate]

  def getPollingDuration: FiniteDuration
}
