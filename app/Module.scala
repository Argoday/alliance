import com.google.inject.AbstractModule
import play.api.{ Configuration, Environment }
import java.time.Clock

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    Config.init(configuration)

  }

}
