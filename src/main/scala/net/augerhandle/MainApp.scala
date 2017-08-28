package net.augerhandle

import akka.actor.{ ActorSystem }

object MainApp extends App {
  val actorSystem = ActorSystem("MainActorSystem")
  val logger = actorSystem.log 
  logger.debug( s"created ${actorSystem.name}")

  this.run()

  logger.debug( s"terminating ${actorSystem.name}" )
  actorSystem.terminate()


  def run() : Unit = 
  {
    val runner = new Runner( actorSystem )
    runner.run()
  }
}

