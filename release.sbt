import scala.sys.process._
import sbtrelease.ReleaseStateTransformations._

lazy val ensureJDK8: ReleaseStep = { st: State =>
  val javaVersion = System.getProperty("java.specification.version")
  if (javaVersion != "1.8") throw new IllegalStateException("Cancelling release, please use JDK 8")
  st
}

releaseCrossBuild := false

releaseProcess := Seq[ReleaseStep](
  ensureJDK8,
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("+test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)

releaseVcsSign := true