name := "play-regex-cors"
organization := "com.github.dgilling"
version := "0.1.1"

publishMavenStyle := true
isSnapshot := true

scalaVersion := "2.11.8"

val playVersion = "2.5.10"

libraryDependencies ++= Seq("com.typesafe.play" %% "play" % playVersion,
  "com.typesafe.play" %% "filters-helpers" % playVersion)
