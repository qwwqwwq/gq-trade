package actors

import java.util.UUID
import java.util.concurrent.TimeUnit

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.testkit.{ ImplicitSender, TestKit }
import apis.ExchangeAPI
import com.google.inject.AbstractModule
import com.mohiva.play.silhouette.api.LoginInfo
import models.{ Exchange, User }
import net.codingwell.scalaguice.ScalaModule
import org.mockito.Mockito
import org.specs2.specification.Scope
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.concurrent.AkkaGuiceSupport
import play.api.test.{ PlaySpecification, WithApplication }

import scala.concurrent.duration.FiniteDuration

class ExchangeWatcherTest() extends TestKit(ActorSystem("ExchangeWatcherTest"))
  with ImplicitSender with PlaySpecification {

  "The ExchangeWatcher" should {
    "initialize properly" in new Context {
      new WithApplication(application) {

        val mockExchangeApi: ExchangeAPI = Mockito.mock(classOf[ExchangeAPI])

        Mockito.when(mockExchangeApi.getPollingDuration)
          .thenReturn(FiniteDuration(1L, TimeUnit.SECONDS))

        Mockito.when(mockExchangeApi.getExchange)
          .thenReturn(Exchange(None, "fake", "api_key"))

        val actor: ActorRef = system.actorOf(
          Props(
            classOf[ExchangeWatcher],
            app.configuration,
            mockExchangeApi,
            identity))

        actor ! ExchangeWatcher.Tick
      }
    }
  }

  /**
   * The context.
   */
  trait Context extends Scope {

    /**
     * A fake Guice module.
     */
    class FakeModule extends AbstractModule with ScalaModule with AkkaGuiceSupport {
      def configure(): Unit = {
        bindActorFactory[ExchangeWatcher, ExchangeWatcher.Factory]
      }
    }

    /**
     * An identity.
     */
    val identity = User(
      userID = UUID.randomUUID(),
      loginInfo = LoginInfo("facebook", "user@facebook.com"),
      firstName = None,
      lastName = None,
      fullName = None,
      email = None,
      avatarURL = None,
      activated = true)

    /**
     * The application.
     */
    lazy val application: Application = new GuiceApplicationBuilder()
      .overrides(new FakeModule)
      .build()
  }

}
