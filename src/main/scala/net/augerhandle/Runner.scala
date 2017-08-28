package net.augerhandle

import akka.actor.{ ActorSystem }
import akka.event.{ LoggingAdapter }


class Runner( actorSystem : ActorSystem ) {
  val log : LoggingAdapter = actorSystem.log
  log.debug( s"Runner created using ActorySystem ${actorSystem.name}" )

  def run() 
  {
    log.info("hi")
  }
}
