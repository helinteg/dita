package dita
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import dita.TelegramUser._
import slick.driver.PostgresDriver.api._
import com.typesafe.config.ConfigFactory


object RunDita extends App {
  val conf = ConfigFactory.load()
  println(conf)

  val db = Database.forConfig("postgres1")
  try {
	val schema = telegram_user.schema // ++ telegram_chat.schema
	db.run(DBIO.seq(
	  schema.create,
	  schema.drop
	))
      } finally db.close
  val token = args(0)
  val bot = new DitaBot(token).run()
  Await.result(bot, Duration.Inf)
}
