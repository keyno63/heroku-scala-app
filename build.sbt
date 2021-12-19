name := "heroku-scala-app"

version := "0.1"

scalaVersion := "2.13.7"

val circeVersion = "0.14.1"
val akkaHttpCirce = "1.38.2"
val tapirVersion = "0.19.1"
val calibanVersion = "1.3.1"
val guiceVersion = "5.0.1"
val zioVersion = "1.0.12"

lazy val akkaSample = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.ghostdogpr" %% "caliban-akka-http"
    ).map(_ % calibanVersion) ++ Seq(
      "de.heikoseeberger" %% "akka-http-circe" % akkaHttpCirce,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
    ) ++ Seq(
      "com.google.inject" % "guice" % guiceVersion,
      "com.typesafe" % "config" % "1.4.1"
    ) ++ Seq(
      "org.postgresql" % "postgresql" % "42.3.1",
      "org.scalikejdbc" %% "scalikejdbc" % "4.0.+",
      "org.scalikejdbc" %% "scalikejdbc-test" % "4.0.+" % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.+",
      // test
      "org.specs2" %% "specs2-core" % "4.13.1" % Test,
      "org.scalameta" %% "munit" % "1.0.0-M1" % Test,
      "dev.zio" %% "zio-test" % zioVersion % Test,
      "org.mockito" % "mockito-core" % "4.1.0" % Test,
      "org.mockito" %% "mockito-scala" % "1.16.46" % Test
    ),
    dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0",
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

//ThisBuild / conflictManager := ConflictManager.latestRevision
Compile / herokuAppName := "heroku-scala-app"
Compile / herokuJdkVersion := "17"
run / fork := true
Test / fork := true
logLevel := Level.Debug
