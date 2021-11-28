package jp.co.keyno.sandbox

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import akka.http.scaladsl.server.Directives._
import com.google.inject.{ Guice, Key }
import jp.co.keyno.sandbox.sample.application.controller.ApiSampleController
import jp.co.keyno.sandbox.sample.Module

object Main extends scala.App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val injector = Guice.createInjector(new Module)
  val controller = injector
    .getInstance(
      Key.get(classOf[ApiSampleController])
    )

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
      } ~ path("controller") {
        get {
          parameters(Symbol("key").?, Symbol("type").?) {
            case (Some(key), None) if key.nonEmpty =>
              key match {
                case "search" => complete(200, controller.findIssueList)
                case _        => complete(200, controller.ok(key))
              }
            case (Some(key), Some(_type)) if _type.nonEmpty =>
              key.toIntOption
                .map(v => complete(200, controller.findIssueListById(v)))
                .getOrElse(complete(400, s"invalid key type [$key]"))
            case _ => complete(400, "not exist key param")
          }
        }
      } ~ path("graphql") {
        // not impl now, will be graphql
        get {
          complete(400, "not impl now")
        } ~ post {
          complete(400, "not impl now")
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
