import javax.inject._

import play.api.http.DefaultHttpErrorHandler
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.routing.Router
import scala.concurrent._

object Config {
  object Stage {
    case object Local extends Stage
    case object Prod extends Stage
  }
  class Stage {
  }

  var stage: Stage = Stage.Local

  def isLocal: Boolean = stage match {
    case Stage.Local => true
    case Stage.Prod => false
  }

  def init(configuration: Configuration) {
    Config.stage = configuration.getString("alliance.stage") match {
      case Some(str) => {
        str match {
          case "local" => {
            System.out.println("Stage: Local")
            Config.Stage.Local
          }
          case "prod" => {
            System.out.println("Stage: Prod")
            Config.Stage.Prod
          }
          case _ => {
            System.err.println("Missing Stage!")
            Config.Stage.Prod
          }
        }
      }
      case None => {
        System.out.println("Stage: Prod")
        Config.Stage.Prod
      }
    }
  }
}

class ErrorHandler @Inject() (
    env: Environment,
    config: Configuration,
    sourceMapper: OptionalSourceMapper,
    router: Provider[Router]
  ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override def onNotFound(request: RequestHeader, message: String): Future[Result] = {
    if (Config.isLocal) {
      Future.successful(NotFound(views.html.defaultpages.devNotFound(request.method, request.uri, Option(router.get))))
    } else {
      Future.successful(NotFound(views.html.notFound()))
    }
  }

}
