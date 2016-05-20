import com.google.inject.AbstractModule
import java.time.Clock
import javax.inject._
import scala.concurrent.Future

import play.api.http.DefaultHttpErrorHandler
import play.api.{ Configuration, Environment, OptionalSourceMapper }
import play.api.mvc.{Result, RequestHeader}
import play.api.mvc.Results.NotFound
import play.api.routing.Router

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {
  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    Config.init(configuration)
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