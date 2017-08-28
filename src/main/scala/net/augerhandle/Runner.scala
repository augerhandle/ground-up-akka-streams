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
    val integerSource = Source(1 to 10)

    // Create a "flow" that adds a big number to the incoming integers, returning an integer.
    val incrementingFlow = Flow[Int].map(
      i_in => 
        {
          val i_out = i_in + 1000
          log.debug( s"incrementing flow: i_in=${i_in}, i_out=${i_out}")
          i_out
        }
      ) 

    // Create another "flow" that only lets even incoming values pass thru.
    val filterOutOddNumbersFlow = Flow[Int].filter( 
      i => 
        {
          val isEven = (i % 2) == 0
          if( isEven )
          {
            log.debug( s"even number, ${i}, permitted to pass thru" )
            true
          }
          else
          {
            log.debug( s"odd number, ${i}, dropped from the flow")
            false
          }
        }
      )

    // Create another "flow" that calculates a running some of all the incoming values.
    val addingFlow = Flow[Int].fold(0)(
      (accum,i_in) =>
        {
          val sum_out = accum + i_in
          log.debug( s"running sum=${sum_out}" )
          sum_out
        }
      )

    // Create yet another "flow" that converts the incoming value into an outgoing string
    val outputFormattingFlow = Flow[Int].collect( 
      { 
        case i_in => s"sum=${i_in}" 
      })

    // Create a "sink" that logs all the incoming values.
    val loggingSink = Sink.foreach[String](s_in => log.info(s"item being processed:${s_in}"))

    val runnableGraph = 
      integerSource
        .via(incrementingFlow)
        .via(filterOutOddNumbersFlow)
        .via(addingFlow)
        .via(outputFormattingFlow)
        .to(loggingSink)

    runnableGraph.run()(actorMaterializer)
  }
}
