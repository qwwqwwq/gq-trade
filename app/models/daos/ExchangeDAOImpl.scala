package models.daos

import java.util.UUID
import javax.inject.Inject

import models.Exchange
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ExchangeDAOImpl @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends ExchangeDAO {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  class Exchanges(tag: Tag) extends Table[Exchange](tag, "EXCHANGES") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def exchangeName = column[String]("NAME")

    def userId = column[String]("USER_ID", O.Unique)

    def name = column[String]("NAME")

    def apiKey = column[String]("API_KEY")

    override def * = (id.?, name, apiKey) <> (Exchange.tupled, Exchange.unapply)
  }

  override def getExchanges(userId: UUID): Future[Seq[Exchange]] = {
    db.run(TableQuery[Exchanges].filter(_.userId === userId.toString).result)
  }
}
