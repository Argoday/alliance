import javax.inject._

import play.api.http.DefaultHttpErrorHandler
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.routing.Router
import scala.concurrent._

object Configuration {
  object Stage {
    case object Local extends Stage
    case object Prod extends Stage
  }
  class Stage {
  }

  def stage: Stage = sys.env.get("STAGE") match {
    case Some(str) => {
      str match {
        case "local" => {
          Stage.Local
        }
        case "prod" => {
          Stage.Prod
        }
        case _ => {
          System.err.println("Missing Stage!")
          Stage.Prod
        }
      }
    }
    case None => {
      Stage.Prod
    }
  }

  def isLocal: Boolean = stage match {
    case Stage.Local => true
    case Stage.Prod => false
  }
}

class ErrorHandler @Inject() (
    env: Environment,
    config: Configuration,
    sourceMapper: OptionalSourceMapper,
    router: Provider[Router]
  ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override def onNotFound(request: RequestHeader, message: String): Future[Result] = {
    System.out.println(s"stage env=${sys.env.get("STAGE")}")
    System.out.println(s"stage=${Configuration.stage.toString}")
    System.out.println(s"conf.islocal=${Configuration.isLocal}")
    if (Configuration.isLocal) {
      Future.successful(NotFound(views.html.defaultpages.devNotFound(request.method, request.uri, Option(router.get))))
    } else {
      Future.successful(NotFound(views.html.notFound()))
    }
  }

}
