package dita
import slick.driver.PostgresDriver.api._


class TelegramUser(tag: Tag) extends Table[(Int)](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey)
  def * = (id)
}

object TelegramUser {
  val telegram_user = TableQuery[TelegramUser]
}
