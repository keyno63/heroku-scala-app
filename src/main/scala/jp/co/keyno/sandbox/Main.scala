package jp.co.keyno.sandbox

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn
import akka.http.scaladsl.server.Directives._

object Main extends scala.App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val route =
    path("") {
      // for health check
      get {
        complete(200, "Ok")
      }
    } ~ pathPrefix("api") {
      path("value") {
        get {
          parameters(Symbol("key").?) {
            case Some(key) if key.nonEmpty => complete(200, s"key: ${key}")
            case _ => complete(400, "not exist key param")
          }
        }
      }
//    } ~ extractRequestContext.flatMap { ctx =>
//        if (ctx.request.uri.path == "apis") {
//          onComplete(Future.successful(""))
//        } else {
//          onComplete(Future.successful("ng"))
//        }
//
//      }
//      extractRequestContext.flatMap { ctx =>
//        onComplete(
//          Future.successful(ctx.request.toString())) flatMap {
//          ret => provide(ret)
//        }
      }


  // start HTTP Server
  val port = 5000
  val bindingFuture = Http()
    .newServerAt("localhost", port)
    .bind(route)
  println(s"start server. port=[${port}]")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
