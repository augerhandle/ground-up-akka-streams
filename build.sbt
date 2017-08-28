lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "net.augerhandle",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ground-up-akka-streams"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-stream_2.12" % "2.5.4",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.akka" % "akka-slf4j_2.12" % "2.5.4")
