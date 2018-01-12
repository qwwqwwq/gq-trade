package actors

import javax.inject.Inject
import akka.actor.Actor
import akka.actor.Timers
import apis.ExchangeAPI
import com.google.inject.assistedinject.Assisted
import models.{ Exchange, User }
import org.log4s
import play.api.Configuration
import org.log4s._
import scala.concurrent.ExecutionContext.Implicits.global

object ExchangeWatcher {
  case object TickKey
  case object Tick

  trait Factory {
    def apply(exchange: ExchangeAPI, user: User): Actor
  }
}

class ExchangeWatcher @Inject() (
  configuration: Configuration,
  @Assisted exchangeApi: ExchangeAPI,
  @Assisted user: User) extends Actor with Timers {

  import ExchangeWatcher._

  private[this] val logger: log4s.Logger = getLogger

  timers.startPeriodicTimer(TickKey, Tick, exchangeApi.getPollingDuration)

  def receive: PartialFunction[Any, Unit] = {
    case Tick => pollExchange()
  }

  def pollExchange(): Unit = {
    MDC.withCtx("exchangeName" -> exchangeApi.getExchange.name) {
      logger.info("Polling exchange.")
    }
    val balances = exchangeApi.getBalances
    val rates = exchangeApi.getRates
  }

  override def preStart(): Unit = {
    super.preStart()

  }
}
