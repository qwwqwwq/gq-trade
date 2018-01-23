package actors

import javax.inject.Inject

import akka.actor.{ Actor, ActorRef, Props }
import akka.pattern.pipe
import apis.ExchangeAPIFactory
import com.google.inject.assistedinject.Assisted
import models.daos.ExchangeDAO
import models.{ Balance, Exchange, Rate, User }
import org.log4s
import org.log4s.getLogger
import play.api.Configuration
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.mutable

object UserTrader {

  case class Init(exchanges: Seq[Exchange]) {}

  case class ExchangeUpdate(
    exchange: Exchange,
    balances: Seq[Balance],
    rates: Seq[Rate]) {}

  trait Factory {
    def apply(user: User): Actor
  }

}

/**
 * Parent actor for a single users trades.
 */
class UserTrader @Inject() (
  configuration: Configuration,
  exchangeDAO: ExchangeDAO,
  exchangeAPIFactory: ExchangeAPIFactory,
  @Assisted user: User) extends Actor {

  import UserTrader._

  private[this] val logger: log4s.Logger = getLogger
  val todo: mutable.Queue[Any] = mutable.Queue()
  var initialized = false
  var exchanges: Option[Seq[Exchange]] = None
  var exchangeActors: Option[Seq[ActorRef]] = None

  def init(exchanges: Seq[Exchange]): Unit = {
    exchangeActors = Some(exchanges.map {
      exchange: Exchange =>
        context.actorOf(Props(
          classOf[ExchangeWatcher],
          configuration,
          exchangeAPIFactory.getExchangeAPI(exchange),
          user))
    })
    initialized = true
    todo.dequeueAll(_ => true).foreach(receive(_))
  }

  override def preStart(): Unit = {
    pipe(exchangeDAO.getExchanges(1).map({ //TODO
      result => Init(result)
    })) to self
  }

  override def receive: PartialFunction[Any, Unit] = {
    case Init(exchanges: Seq[Exchange]) if initialized =>
      init(exchanges)
    case ExchangeUpdate if initialized =>
      logger.info("Received exchange update.")
    case _ if !initialized =>
      todo.enqueue(_)
  }
}
