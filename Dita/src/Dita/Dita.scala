package dita
import com.bot4s.telegram.api.declarative.{Commands, RegexCommands}
import com.bot4s.telegram.api.Polling
import com.bot4s.telegram.api.TelegramBot
import java.net.{Proxy,InetSocketAddress}
import com.bot4s.telegram.clients.ScalajHttpClient

import scala.util.Try


class DitaBot(val token: String) extends TelegramBot
  with Polling
  with RegexCommands
  with Commands {
  val proxy = new Proxy(Proxy.Type.SOCKS, InetSocketAddress.createUnresolved("localhost", 9050))
  val client = new ScalajHttpClient(token, proxy)
  onRegex("""\+""".r) {
    implicit msg =>
	_ =>
	reply("pr")
  }
}
