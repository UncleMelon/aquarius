import akka.actor.ActorSystem

/**
* 
* @author matthew_wu
* @since 2019-07-16 11:04
*/
package object myakka {
  lazy val ourSystem = ActorSystem("OurExampleSystem")

}
