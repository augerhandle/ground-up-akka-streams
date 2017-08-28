package net.augerhandle

import akka.actor.{ ActorSystem }
import akka.event.{ LoggingAdapter }
import akka.stream.{ ActorMaterializer }
import akka.stream.scaladsl.{ Source, Sink, Flow }


class Runner( actorSystem : ActorSystem ) {
  val actorMaterializer = ActorMaterializer()(actorSystem)
  val log : LoggingAdapter = actorSystem.log

  log.debug( s"Runner created using ActorySystem ${actorSystem.name}" )

  def run() 
  {
    // Create a "source" containing a bunch of integers.
    val source = Source(1 to 10)

    // Create a "flow" that adds a big number to the incoming integers, returning an integer.
    val flow1 = Flow[Int].map(i => i + 1000) 

    // Create another "flow" that only lets even incoming values pass thru.
    val flow2 = Flow[Int].filter( i => i%2==0)

    // Create a "sink" that adds all the incoming values.
    val sink = Sink.foreach[Int](i => log.info(s"item being processed: ${i}"))

    val runnableGraph = 
      source
        .via(flow1)
        .via(flow2)
        .to(sink)

    runnableGraph.run()(actorMaterializer)
  }
}
