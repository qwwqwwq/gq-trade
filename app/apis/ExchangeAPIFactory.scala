package apis

import models.Exchange

trait ExchangeAPIFactory {

  def getExchangeAPI(exchange: Exchange): ExchangeAPI
}
