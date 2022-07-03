val sharedSettings = Seq(
  organization := "com.github.plokhotnyuk.play-json-extensions",
  name := "play-json-extensions",
  scalaVersion := "2.12.10",
  crossScalaVersions := Seq("2.12.10", "2.13.1"),
  credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential"),
  description := "Additional type classes for the play-json serialization library",
  organizationHomepage := Some(url("https://github.com/plokhotnyuk")),
  homepage := Some(url("https://github.com/plokhotnyuk/play-json-extensions")),
  startYear := Some(2015),
  licenses += ("Two-clause BSD-style license", url("https://github.com/plokhotnyuk/blob/master/LICENSE.txt")),
  developers := List(
    Developer("cvogt", "Jan Christopher Vogt", "@cvogt", url("https://github.com/cvogt"))
  ),
  libraryDependencies ++= Seq(
    "com.typesafe.play" %%% "play-json" % "2.9.2",
    "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
    "org.scalatest" %%% "scalatest" % "3.2.12" % "test"
  ),
  scalacOptions ++= Seq(
    "-feature", "-deprecation", "-unchecked",
    "-language:experimental.macros",
    "-Ywarn-unused:imports",
    "-Xfatal-warnings"
  ),
  Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oFD"),
  packageOptions += Package.ManifestAttributes("Automatic-Module-Name" -> moduleName.value),
  sonatypeProfileName := "com.github.plokhotnyuk",
  versionScheme := Some("early-semver"),
  publishTo := sonatypePublishToBundle.value,
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false },
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/plokhotnyuk/play-json-extensions"),
      "scm:git@github.com:plokhotnyuk/play-json-extensions.git"
    )
  )
)

lazy val root = Project("play-json-extensions", file("."))
  .aggregate(`play-json-extensions`.js, `play-json-extensions`.jvm)
  .settings(sharedSettings)
  .settings(publish / skip := true)

lazy val `play-json-extensions` = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(sharedSettings)
