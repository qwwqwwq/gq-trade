package utils

import models.Exchange
import play.api.libs.json.{ Json, OFormat }

/**
 * Formatters for ScalaPB generated types.
 *
 * There does not seem to be a easy way to automatically generate these.
 */
object ScalaPBJson {
  implicit val exchangeFormat: OFormat[Exchange] = Json.format[Exchange]
}
