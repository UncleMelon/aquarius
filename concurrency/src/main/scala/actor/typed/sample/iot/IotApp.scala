package actor.typed.sample.iot

import akka.actor.typed.ActorSystem


/**
  *
  * @author matthew_wu
  * @since 2019-08-13 18:03
  */
object IotApp {

  def main(args: Array[String]): Unit = {
    // Create ActorSystem and top level supervisor
    ActorSystem[Nothing](IotSupervisor(), "iot-system")
  }
}
