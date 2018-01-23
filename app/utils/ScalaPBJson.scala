package utils

import com.trueaccord.scalapb.{ GeneratedMessage, GeneratedMessageCompanion, Message }
import play.api.libs.json._
import com.trueaccord.scalapb.json.JsonFormat

object ScalaPBJson {

  implicit val scalaPBWrites: Writes[_ <: GeneratedMessage with Message[_] with GeneratedMessageCompanion[_]] = new Writes[_ <: GeneratedMessage with Message[_] with GeneratedMessageCompanion[_]] {
    override def writes(o: _ <: GeneratedMessage with Message[_] with GeneratedMessageCompanion[_]): JsValue = {
      Json.parse(JsonFormat.toJsonString(o))
    }
  }
}
