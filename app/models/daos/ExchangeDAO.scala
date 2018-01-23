package models.daos

import java.util.UUID

import models.Exchange

import scala.concurrent.Future

trait ExchangeDAO {

  def getExchanges(userId: Int): Future[Seq[Exchange]]
}
