package net.augerhandle

import scala.concurrent.duration.{ Duration }
import scala.concurrent.{ Future, Await }
import akka.{ NotUsed, Done }
import akka.actor.{ ActorSystem }
import akka.event.{ LoggingAdapter }
import akka.stream.{ ActorMaterializer }
import akka.stream.scaladsl.{ Source, Sink, Flow, RunnableGraph, Keep }


class Runner( actorSystem : ActorSystem ) {
  val log : LoggingAdapter = actorSystem.log

  def run() 
  {
    // Create a "source", several "flows" and a "sink".
    val source : Source[Int, NotUsed]         = Source(1 to 10)
    val flow1  : Flow[Int, Int, NotUsed]      = Flow[Int].map( i => i + 1 )
    val flow2  : Flow[Int, Int, NotUsed]      = Flow[Int].filter( i => i % 2 == 0 )
    val flow3  : Flow[Int, Double, NotUsed]   = Flow[Int].map( i => i * 3.0 )
    val sink   : Sink[Double, Future[Double]] = Sink.fold[Double,Double](0.0)( updateRunningSum )

    // wire up the components into a graph
    val graph : RunnableGraph[Future[Double]] = 
      source
        .via(flow1)
        .via(flow2)
        .via(flow3)
        .toMat(sink)(Keep.right)

    // run the graph
    log.debug( s"Running the graph using ActorySystem ${actorSystem.name}" )
    implicit val actorMaterializer = ActorMaterializer()(actorSystem)
    val futureGraphResult : Future[Double] = graph.run()

    val graphResult : Double = Await.result(futureGraphResult, Duration(100, "millis") )
    log.info( s"got graph result: sum=${graphResult}")
  }

  // add two numbers, but log the details 
  def updateRunningSum( initialRunningSum : Double, newItem : Double ) : Double =
  {
    val updatedRunningSum : Double = initialRunningSum + newItem
    log.debug( s"${initialRunningSum} + ${newItem} = ${updatedRunningSum}" )

    updatedRunningSum
  }
}
