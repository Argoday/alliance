package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeController @Inject() extends Controller {

  def ping = Action {
    Ok("")
  }

  def robots = controllers.Assets.versioned(path="/public", file="robots.txt")

  def index = Action {
    Ok(views.html.index())
  }

}
