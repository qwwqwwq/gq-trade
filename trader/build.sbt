name := "trader"

lazy val akkaVersion = "2.5.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.play" %% "play-guice" % "2.6.10",
  "net.codingwell" %% "scala-guice" % "4.1.0"
)
