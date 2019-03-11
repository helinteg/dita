package dita
import scala.concurrent.Await
import scala.concurrent.duration.Duration


object Main extends App {
  val token = args(0)
  val bot = new Dita(token).run()
  Await.result(bot, Duration.Inf)
}
