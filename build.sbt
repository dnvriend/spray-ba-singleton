import akka.sbt.AkkaKernelPlugin.{ Dist, distJvmOptions, distMainClass}

organization := "com.github.dnvriend"

name := "spray-ba-singleton"

version := "0.0.1"

scalaVersion := "2.11.2"

resolvers += "spray" at "http://repo.spray.io/"

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"

libraryDependencies ++=
  {	val scalaV = "2.11.1"
    val akkaV = "2.3.5"
    val sprayV = "1.3.1"
    val shapelessV = "2.0.0"
    val jsonV = "1.2.6"
    Seq(
      "org.scala-lang" % "scala-library" % scalaV,
      "com.typesafe.akka" %% "akka-kernel" % akkaV,
      "com.typesafe.akka" %% "akka-actor" %  akkaV,
      "com.typesafe.akka" %% "akka-slf4j" % akkaV,
      "com.typesafe.akka" %% "akka-cluster" % akkaV,
      "com.typesafe.akka" %% "akka-contrib" % akkaV,
      "com.github.krasserm" %% "akka-persistence-cassandra" % "0.3.2",
      "io.spray" %% "spray-http" % sprayV,
      "io.spray" %% "spray-httpx" % sprayV,
      "io.spray" %% "spray-routing-shapeless2" % sprayV,
      "io.spray" %% "spray-util" % sprayV,
      "io.spray" %% "spray-io" % sprayV,
      "io.spray" %% "spray-can" % sprayV,
      "io.spray" %% "spray-client" % sprayV,
      "io.spray" %% "spray-json" % jsonV,
      "com.github.scala-blitz" %% "scala-blitz" % "1.1",
      "org.apache.shiro" % "shiro-all" % "1.2.3"
    )
  }

autoCompilerPlugins := true

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

publishMavenStyle := true

publishArtifact in Test := false

net.virtualvoid.sbt.graph.Plugin.graphSettings

akka.sbt.AkkaKernelPlugin.distSettings

distJvmOptions in Dist := "-Xms64M -Xmx128M"

distMainClass in Dist := "akka.kernel.Main com.example.Main"

