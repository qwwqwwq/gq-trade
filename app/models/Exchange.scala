package models

import play.api.libs.json.{ Json, OFormat }

case class Exchange(
  id: Option[Int] = None,
  name: String,
  apiKey: String) {

}

object Exchange {
  implicit val jsonFormat: OFormat[Exchange] = Json.format[Exchange]
}
