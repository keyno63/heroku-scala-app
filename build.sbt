name := "heroku-scala-app"

version := "0.1"

scalaVersion := "2.13.7"

val circeVersion = "0.14.1"
val akkaHttpCirce = "1.38.2"
val calibanVersion = "1.2.2"
val guiceVersion = "4.2.2"

lazy val akkaSample = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.ghostdogpr" %% "caliban-akka-http"
    ).map(_ % calibanVersion) ++ Seq(
      "de.heikoseeberger"            %% "akka-http-circe"               % akkaHttpCirce,
    ),
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

Compile / herokuAppName := "heroku-scala-app"
Compile / herokuJdkVersion := "17"
run / fork := true
Test / fork := true
logLevel := Level.Debug
