package spray.examples

import scala.concurrent.duration._
import akka.actor.Actor
import spray.can.Http
import spray.json._
import spray.http._
import HttpMethods._
import StatusCodes._

class BenchmarkService extends Actor {
  import context.dispatcher // ExecutionContext for scheduler
  import Uri._
  import Uri.Path._

  println("Starting...")
  
  def jsonResponseEntity = HttpEntity(
    contentType = ContentTypes.`application/json`,
    string = JsObject("message" -> JsString("Hello, World!")).compactPrint)

  def fastPath: Http.FastPath = {
    case HttpRequest(GET, Uri(_, _, Slash(Segment("fast-ping", Path.Empty)), _, _), _, _, _) =>
      HttpResponse(entity = "FAST-PONG!")

    case HttpRequest(GET, Uri(_, _, Slash(Segment("fast-json", Path.Empty)), _, _), _, _, _) =>
      HttpResponse(entity = jsonResponseEntity)
  }

  def receive = {
    // when a new connection comes in we register ourselves as the connection handler
    case _: Http.Connected => sender ! Http.Register(self, fastPath = fastPath)

    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) => sender ! HttpResponse(entity = "PONG!")
//    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) => sender ! HttpResponse(entity = "PONG!")

    case HttpRequest(GET, Uri.Path("/json"), _, _, _) => sender ! HttpResponse(entity = jsonResponseEntity)

    case HttpRequest(GET, Uri.Path("/stop"), _, _, _) =>
      sender ! HttpResponse(entity = "Shutting down in 1 second ...")
      context.system.scheduler.scheduleOnce(1.second) { context.system.shutdown() }

    case _: HttpRequest => sender ! HttpResponse(entity = "loaderio-68189387fda4794cea5458c058a138c7")
  }
}
