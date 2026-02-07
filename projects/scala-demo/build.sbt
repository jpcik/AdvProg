ThisBuild / version := "1.1"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "ch.hevs"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.8.0" ,
    "dev.zio" %% "zio" % "2.0.13",
    "org.scalatest" %% "scalatest" % "3.2.14" % Test
)

