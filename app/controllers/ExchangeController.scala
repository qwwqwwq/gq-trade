package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Silhouette
import models.daos.ExchangeDAO
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.auth.DefaultEnv

class ExchangeController @Inject() (
                                     components: ControllerComponents,
                                    silhouette: Silhouette[DefaultEnv],
                                     exchangeDAO: ExchangeDAO)
  extends AbstractController(components) with I18nSupport {

  def view = silhouette.SecuredAction.async {
    implicit request =>
      exchangeDAO.getExchanges(request.identity.userID).map(exchanges =>
        Ok(Json.toJson(exchanges)))
  }
}
