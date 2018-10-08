package dita
import scala.concurrent.Await
import scala.concurrent.duration.Duration


object RunDita extends App {
  val token = args(0)
  val bot = new DitaBot(token).run()
  Await.result(bot, Duration.Inf)
}
