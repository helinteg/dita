package dita
import com.bot4s.telegram.api.declarative.{Commands, RegexCommands}
import com.bot4s.telegram.api.Polling
import com.bot4s.telegram.api.TelegramBot
import java.net.{InetSocketAddress, Proxy}

import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.models.Message

import scala.util.Try

trait PerKarmaWhores[S] {
  private val KarmaWhores = collection.mutable.Map[Int, S]()
  def setKarmaWhores(value: S)(implicit msg: Message): Unit = atomic {

    msg.replyToMessage.get.from.foreach{
      user => {
        KarmaWhores.update(user.id, value)
      }
    }
  }

//  def clearKarmaWhores(implicit msg: Message): Unit = atomic {
//    KarmaWhores.remove(msg.from.foreach{user => user.id})
//  }

  private def atomic[T](f: => T): T = KarmaWhores.synchronized {
    f
  }

  def withKarmaWhores(f: Option[S] => Unit)(implicit msg: Message): Unit = f(getKarmaWhores)

  def getKarmaWhores(implicit msg: Message): Option[S] = atomic {
    var user_id = 0
    msg.replyToMessage.get.from.foreach{
      user => {
        user_id = user.id
      }
    }
    KarmaWhores.get(user_id)
  }
}

class KarmaWhore(var user_id: Int)
{
  var karma = 0

  def getKarma(): Int = {
    return karma
  }
  def setKarma(karmaChange: Int){
    karma += karmaChange
  }
}

class DitaBot(val token: String) extends TelegramBot
  with Polling
  with RegexCommands
  with Commands
  with PerKarmaWhores[Int] {
  val proxy = new Proxy(Proxy.Type.SOCKS, InetSocketAddress.createUnresolved("83.149.198.207", 9050))
  val client = new ScalajHttpClient(token, proxy)
  onRegex(""".*\+.*""".r) {
    implicit msg =>
	_ =>{

      val replyMsgExist = msg.replyToMessage.isEmpty
      if (!replyMsgExist){
        msg.replyToMessage.get.from.foreach{
          user => {
            val previousKarma = getKarmaWhores
            setKarmaWhores(previousKarma.getOrElse(0) + 1)
            val currentKarma = getKarmaWhores
            reply(currentKarma.getOrElse(0).toString)
          }
        }
      }

    }
  }
  onMessage{
    implicit msg => {
      println(msg.text.getOrElse(None))
      msg.from.foreach{
        user => {
          println(user.id)
          println(user.username.getOrElse(None))
          println(user.firstName)
          println(user.lastName.getOrElse(None))
        }
      }
      val replyMsgExist = msg.replyToMessage.isEmpty
        if (!replyMsgExist){
          msg.replyToMessage.get.from.foreach{
              user => {
                println(user.id)
                println(user.username.getOrElse(None))
                println(user.firstName)
                println(user.lastName.getOrElse(None))
              }
          }
      }
      else
        println("""Not a reply""")
      println("""================""")
    }
  }
}
