package net.augerhandle

import akka.actor.{ ActorSystem }
import akka.event.{ LoggingAdapter }
import akka.stream.{ ActorMaterializer }
import akka.stream.scaladsl.{ Source, Sink, Flow }


class Runner( actorSystem : ActorSystem ) {
  val log : LoggingAdapter = actorSystem.log

  def run() 
  {
    // Create a "source", several "flows" and a "sink".
    val source = Source(1 to 10)
    val flow1 = Flow[Int].map( _ + 1 )
    val flow2 = Flow[Int].filter( _ % 2 == 0 )
    val flow3 = Flow[Int].map( _ * 3.0 )
    val sink = Sink.foreach[Double]( x => log.info( s"x=${x}" ))

    // wire up the components into a graph
    val runnableGraph = 
      source
        .via(flow1)
        .via(flow2)
        .via(flow3)
        .to(sink)

    // run the graph
    log.debug( s"Running the graph using ActorySystem ${actorSystem.name}" )
    implicit val actorMaterializer = ActorMaterializer()(actorSystem)
    runnableGraph.run()
  }
}
