organization in ThisBuild := "com.gqtrade"

version in ThisBuild := "1.0"

scalaVersion in ThisBuild := "2.12.4"

lazy val root = (project in file("."))
  .aggregate(web, trader)

lazy val models = (project in file("models"))

lazy val web = (project in file("web"))
  .dependsOn(models)

lazy val trader = (project in file("trader"))
  .dependsOn(models)
