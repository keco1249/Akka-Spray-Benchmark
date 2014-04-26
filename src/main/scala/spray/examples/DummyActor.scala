package spray.examples

import akka.actor.Actor

class DummyActor extends Actor {
  
 def receive = {
    // when a new connection comes in we register ourselves as the connection handler
    case s:String => 

  }
}