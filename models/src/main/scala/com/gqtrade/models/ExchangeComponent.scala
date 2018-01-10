package com.gqtrade.models

import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import slick.lifted.Tag

case class Exchange(id: Option[Int] = None,
                    name: String,
                    apiKey: String)

class Exchanges(tag: Tag) extends Table[Exchange](tag, "EXCHANGES") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def userId = column[Int]("USER_ID", O.Unique)
  def name = column[String]("NAME")
  def apiKey = column[String]("API_KEY")

  override def * = (id.?, name, apiKey) <> (Exchange.tupled, Exchange.unapply)
}
