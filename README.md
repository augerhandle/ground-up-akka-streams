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

## This is STEP-2. What's in it?

In the step we introduce some basics of Akka. This involves

* package dependencies
* package imports
* creating and terminating an ActorSystem (which will be required when we start using Akka Streams)
* and a bit of logging (using Akka logging, which ensures your logging is ansynchronous)

(Use git diff to see what was added since the last step.)

## What's in previous steps?

* step-1 : just a hello world app

## Building and Running

This is an SBT-based Scala project. But it's not fancy. The following targets should be sufficient:

* `sbt clean`
* `sbt run`
