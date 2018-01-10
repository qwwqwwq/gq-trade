package com.gqtrade.actors

import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.Timers

class ExchangeWatcher extends Actor with Timers {
  private case object TickKey
  private case object Tick

  timers.startPeriodicTimer(TickKey, Tick, 500.millis)

  def receive: PartialFunction[Any, Unit] = {
    case Tick ⇒
    // do something useful here
  }
}
