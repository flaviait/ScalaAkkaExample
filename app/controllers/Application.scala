package controllers

import play.api._
import play.api.mvc._
import play.libs.Akka
import akka.actor.Props
import actors.{Message, HelloWorldActor}

import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import akka.routing.RoundRobinRouter

object Application extends Controller {

  lazy val helloWorldActor = Akka.system.actorOf(Props.create(classOf[HelloWorldActor]).withRouter(RoundRobinRouter(5)),"HelloWorldActor")

  def index = Action.async {
    implicit val timeout = Timeout(5 seconds)

    val answer: Future[Any] = helloWorldActor ? Message("Code Meetup")

    answer.map ({
      x: Any => {
        Ok(views.html.index( x.toString))
      }
    })
  }

}