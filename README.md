# ground-up-akka-streams

## What is this?

A simple, step-by-step "getting started" introduction to Akka Streams.

* I used this myself to gradually build up my own understanding.
* The "steps" are built up incrementally in branches named "step-N".
* They start from a simple hello-world application with no Akka Streams.
* The steps are intentionally very modest, only a few things are introduced in each one.
* (Sorry folks, this is how my brain learns things.)

## Assumptions

* You know Scala.
* You know SBT.
* You're trying to figure out how to understand Akka Streams but are looking for something different than their Getting Started guide.

## This is STEP-5. What's in it?

In the step we change the sink to be slightly more complex. Instead of just printing/logging all of the items in the stream, it 
folds over the stream to calculate a running sum. That means that when the graph is run, the result returned by the `run()`` method
is the final aggregate sum. (Actually it's a `Future`, since aggregate calculations over streams could in practice take a while to complete.)

We need to do some extra work to ensure that the materialized result of the sink (the aggregate sum) is what is returned by the graph when it is run. So we use `toMat()` instead of `to()` when we wire the sink up to the graph. 

Finally, because the result of running the graph is a `Future` (of the aggregate sum), in order to get the sum itself, we need to *await* on the result of that future.

That's the only essential difference between this and the previous step.

(Use git diff to see what was added since the last step.)

## What's in previous steps?

* STEP-4 : be explicit about types
* STEP-3 : construct and run a simple Akka streams graph
* STEP-2 : getting started with some simple Akka (non-streams) 
* STEP-1 : a (non-Akka) hello world app

## Building and Running

This is an SBT-based Scala project. But it's not fancy. The following targets should be sufficient:

* `sbt clean`
* `sbt run`
