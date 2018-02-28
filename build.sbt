name := """warframe-market"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test,
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "mysql" % "mysql-connector-java" % "5.1.36"
)


