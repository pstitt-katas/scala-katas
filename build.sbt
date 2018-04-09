
lazy val globalSettings = Seq(
  organization := "stittp.katas",
  scalacOptions ++= Seq("-encoding", "UTF-8",
    "-language:postfixOps",
    "-language:higherKinds",
    "-feature",
    "-unchecked",
    "-deprecation",
    "-Xlint",
    "-Xfuture",
    //"-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xmax-classfile-name",
    "100"),

  resolvers ++= Seq(
    Opts.resolver.mavenLocalFile,
    "Artima Maven Repository" at "http://repo.artima.com/releases",
    Resolver.bintrayRepo("cakesolutions", "maven"),
    "spray repo" at "http://repo.spray.io"
  ),
  parallelExecution in Test := false,
  updateOptions := updateOptions.value.withCachedResolution(true)
)

lazy val root = (project in file("."))
  .settings(
    name := "scala-katas",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
      "org.mockito" % "mockito-all" % "1.10.19" % "test",
      "org.slf4j" % "slf4j-api" % "1.8.0-beta2"
    )
  )
.settings(globalSettings)
