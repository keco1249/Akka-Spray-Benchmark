package spray.examples

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem()

  // the handler actor replies to incoming HttpRequests
  val handler = system.actorOf(Props[BenchmarkService], name = "handler")

  IO(Http) ! Http.Bind(handler, "0.0.0.0", 8080)
}
