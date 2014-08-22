package com.example

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.contrib.pattern.ClusterSingletonProxy
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Test2 extends App {
  implicit val system = ActorSystem("ClusterSystem", ConfigFactory.load("application2.conf"))
  implicit val ec = system.dispatcher
  implicit val ko = Timeout(3, TimeUnit.SECONDS)

  val securityServiceView = system.actorOf(ClusterSingletonProxy.props("/user/singletonSecurityServiceView/securityServiceView", None), "securityServiceViewProxy")

  val future = securityServiceView ? SecurityServiceView.GetAllUsers
  val ready = Await.ready(future, Duration(2, TimeUnit.SECONDS))
  ready.map { s => println("Ready: " + s)}
  ready.failed.map { t => println("Error: " + t)}

  system.shutdown()
}
