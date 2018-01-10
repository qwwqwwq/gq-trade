package models.daos

import java.util.UUID

import models.Exchange

trait ExchangeDAO {

  def getExchanges(userId: UUID): Set[Exchange]
}
