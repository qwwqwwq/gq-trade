package com.gqtrade.apis

import com.gqtrade.models.{Address, Balance, Coin, Order}

trait ExchangeAPI {

  def getActiveOrders: Set[Order]

  def createOrder: Order

  def getOrder(orderId: String): Order

  def cancelOrder(orderId: String): Boolean

  def getBalances: Set[Balance]

  def getBalance: Balance

  def getAddress(coin: Coin): Address
}
