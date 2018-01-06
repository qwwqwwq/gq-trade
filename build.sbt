lazy val commonSettings = Seq(
  organization := "com.gqtrade",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.3"
)

lazy val root = (project in file("."))
  .aggregate(web, trader)

lazy val models = (project in file("models"))
  .settings(commonSettings)

lazy val web = (project in file("web"))
  .settings(commonSettings)
  .dependsOn(models)

lazy val trader = (project in file("trader"))
  .settings(commonSettings)
  .dependsOn(models)
