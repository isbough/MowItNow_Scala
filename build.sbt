ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "MowItNow_Scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
