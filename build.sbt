name := "heroku-scala-app"

version := "0.1"

scalaVersion := "2.13.7"

val circeVersion = "0.14.1"
val akkaHttpCirce = "1.38.2"
val calibanVersion = "1.2.2"
val guiceVersion = "5.0.1"

lazy val akkaSample = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.ghostdogpr" %% "caliban-akka-http"
    ).map(_ % calibanVersion) ++ Seq(
      "de.heikoseeberger" %% "akka-http-circe" % akkaHttpCirce,
    ) ++ Seq(
      "com.google.inject" % "guice" % guiceVersion,
    ) ++ Seq(
      "org.postgresql" % "postgresql" % "42.3.1",
      "org.scalikejdbc" %% "scalikejdbc" % "4.0.+",
      "org.scalikejdbc" %% "scalikejdbc-test" % "4.0.+" % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.+",
      // test
      "org.specs2" %% "specs2-core" % "4.13.0" % "test"
    ),
    dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"
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
