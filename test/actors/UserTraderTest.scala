package actors

import java.util.UUID

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.testkit.{ ImplicitSender, TestKit }
import apis.{ ExchangeAPI, ExchangeAPIFactory }
import com.google.inject.AbstractModule
import com.mohiva.play.silhouette.api.LoginInfo
import models.{ Exchange, User }
import models.daos.ExchangeDAO
import net.codingwell.scalaguice.ScalaModule
import org.mockito.{ Matchers, Mockito }
import org.specs2.specification.Scope
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.concurrent.AkkaGuiceSupport
import play.api.test.{ PlaySpecification, WithApplication }

import scala.concurrent.Future

class UserTraderTest extends TestKit(ActorSystem("UserTraderTest"))
  with ImplicitSender with PlaySpecification {

  "The UserTrader" should {
    "receive init" in new Context {
      new WithApplication(application) {

        val mockExchangeAPI: ExchangeAPI = Mockito.mock(classOf[ExchangeAPI])

        Mockito.when(mockExchangeDao.getExchanges(Matchers.eq(identity.userID)))
          .thenReturn(Future.successful(Seq(Exchange(name = "test", apiKey = "APIKEY"))))

        Mockito.when(mockExchangeApiFactory.getExchangeAPI(Matchers.any()))
          .thenReturn(mockExchangeAPI)

        val actor: ActorRef = system.actorOf(
          Props(
            classOf[UserTrader],
            app.configuration,
            app.injector.instanceOf[ExchangeDAO],
            app.injector.instanceOf[ExchangeAPIFactory],
            identity))
      }
    }
  }

  /**
   * The context.
   */
  trait Context extends Scope {

    val mockExchangeApiFactory: ExchangeAPIFactory =
      Mockito.mock(classOf[ExchangeAPIFactory])
    val mockExchangeDao: ExchangeDAO =
      Mockito.mock(classOf[ExchangeDAO])

    /**
     * A fake Guice module.
     */
    class FakeModule extends AbstractModule with ScalaModule with AkkaGuiceSupport {
      def configure(): Unit = {
        bind[ExchangeAPIFactory].toInstance(mockExchangeApiFactory)
        bind[ExchangeDAO].toInstance(mockExchangeDao)
        bindActorFactory[UserTrader, UserTrader.Factory]

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
