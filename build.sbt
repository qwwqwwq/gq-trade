import play.sbt.PlayScala

organization in ThisBuild := "com.gqtrade"

version in ThisBuild := "1.0"

scalaVersion in ThisBuild := "2.12.4"

lazy val root = (project in file("."))
  .aggregate(web, trader)

lazy val models = (project in file("models"))
  .enablePlugins(SbtScalariform)

lazy val web = (project in file("web"))
  .dependsOn(models)
  .enablePlugins(PlayScala, SbtScalariform)

lazy val trader = (project in file("trader"))
  .dependsOn(models)
  .enablePlugins(SbtScalariform)
