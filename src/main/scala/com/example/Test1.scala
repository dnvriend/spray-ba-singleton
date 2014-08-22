package com.example

import akka.actor.{ActorSystem, PoisonPill}
import akka.contrib.pattern.ClusterSingletonManager
import com.typesafe.config.ConfigFactory

object Test1 extends App {
  implicit val system = ActorSystem("ClusterSystem", ConfigFactory.load("application.conf"))

  system.actorOf(ClusterSingletonManager.props(
    singletonProps = SecurityServiceView.props,
    singletonName = "securityServiceView",
    terminationMessage = PoisonPill,
    role = None),
    name = "singletonSecurityServiceView")
}
