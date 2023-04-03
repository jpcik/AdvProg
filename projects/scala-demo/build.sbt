ThisBuild / version := "1.1"
ThisBuild / scalaVersion := "3.1.3"
ThisBuild / organization := "ch.hevs"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.8.0" ,
    "org.scalatest" %% "scalatest" % "3.2.14" % Test
)

