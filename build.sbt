name := "Spray Benchmark"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "spray repo" at "http://repo.spray.io/"

atmosSettings

traceAkka("2.2.3")

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-remote_2.10" % "2.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-kernel_2.10" % "2.2.3"

libraryDependencies += "com.typesafe.akka" % "akka-cluster_2.10" % "2.2.3"

libraryDependencies += "io.spray" %%  "spray-json" % "1.2.5"

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.2-RC4"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test"
  )
}

seq(Revolver.settings: _*)
