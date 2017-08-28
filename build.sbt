lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "net.augerhandle",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ground-up-akka-streams"
  )
