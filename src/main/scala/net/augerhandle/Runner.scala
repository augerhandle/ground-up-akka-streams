package net.augerhandle

import scala.concurrent.{ Future }
import akka.{ NotUsed, Done }
import akka.actor.{ ActorSystem }
import akka.event.{ LoggingAdapter }
import akka.stream.{ ActorMaterializer }
import akka.stream.scaladsl.{ Source, Sink, Flow, RunnableGraph }


class Runner( actorSystem : ActorSystem ) {
  val log : LoggingAdapter = actorSystem.log

  def run() 
  {
    // Create a "source", several "flows" and a "sink".
    val source : Source[Int, NotUsed]       = Source(1 to 10)
    val flow1  : Flow[Int, Int, NotUsed]    = Flow[Int].map( i => i + 1 )
    val flow2  : Flow[Int, Int, NotUsed]    = Flow[Int].filter( i => i % 2 == 0 )
    val flow3  : Flow[Int, Double, NotUsed] = Flow[Int].map( i => i * 3.0 )
    val sink   : Sink[Double, Future[Done]] = Sink.foreach[Double]( x => log.info( s"x=${x}" ))

    // wire up the components into a graph
    val graph : RunnableGraph[NotUsed] = 
      source
        .via(flow1)
        .via(flow2)
        .via(flow3)
        .to(sink)

    // run the graph
    log.debug( s"Running the graph using ActorySystem ${actorSystem.name}" )
    implicit val actorMaterializer = ActorMaterializer()(actorSystem)
    val graphResult : NotUsed = graph.run()
  }
}
