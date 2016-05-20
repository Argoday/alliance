import play.api.Configuration

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

