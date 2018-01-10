name := """models"""

mainClass in Compile := Some("SimpleExample")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.h2database" % "h2" % "1.4.187",
  "org.xerial" % "sqlite-jdbc" % "3.8.7",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.3"
)

fork in run := true
