package filters

import javax.inject.Inject

import akka.stream.Materializer
import play.api.mvc.{ Filter, RequestHeader, Result }

import scala.concurrent.{ ExecutionContext, Future }
import org.log4s._

class AccessLogFilter @Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  private[this] val logger = getLogger

  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    logger.debug(requestHeader.toString())
    nextFilter(requestHeader)
  }
}
