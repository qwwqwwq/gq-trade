package actors

import javax.inject.Inject

import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.Timers
import apis.ExchangeAPI
import com.google.inject.assistedinject.Assisted
import models.{Exchange, User}
import play.api.Configuration
import play.api.Logger
import org.log4s._

object ExchangeWatcher {

  trait Factory {
    def apply(exchange: ExchangeAPI, user: User): Actor
  }
}

class ExchangeWatcher @Inject() (configuration: Configuration,
                                 @Assisted exchangeApi: ExchangeAPI,
                                 @Assisted user: User) extends Actor with Timers {
  private[this] val logger = getLogger

  private case object TickKey
  private case object Tick

  timers.startPeriodicTimer(TickKey, Tick, exchangeApi.getPollingDuration)

  def receive: PartialFunction[Any, Unit] = {
    case Tick => pollExchange()
  }

  def pollExchange(): Unit = {
    val balances = exchangeApi.getBalances
    val rates = exchangeApi.getRates
  }

  override def preStart(): Unit = {
    super.preStart()

  }
}
