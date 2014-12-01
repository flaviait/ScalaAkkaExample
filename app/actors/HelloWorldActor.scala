package actors

import akka.actor.{ActorLogging, Actor}

case class Message(m: String)

/**
 * Created by tkilian on 11/19/14.
 */
class HelloWorldActor extends Actor with ActorLogging {

  var count: Int = 0

  override def receive: Actor.Receive = {
    case Message(message) => {
      log.warning(s"Hallo $message")
      count = count + 1
      sender ! s"huhu $message from $self"
    }
  }
}
