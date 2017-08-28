# grount-up-akka-streams

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

## This is STEP-3. What's in it?

In the step we introduce some basics of Akka Streams. This involves

* creating a "source" with several items in it
* specifying the processing to happen on each item in the source
* running it

(Use git diff to see what was added since the last step.)

## What's in previous steps?

* STEP-2 : getting started with some simple Akka (non-streams) 
* STEP-1 : a (non-Akka) hello world app

## Building and Running

This is an SBT-based Scala project. But it's not fancy. The following targets should be sufficient:

* `sbt clean`
* `sbt run`
