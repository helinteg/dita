// build.sc
import mill._, scalalib._

object Dita extends ScalaModule{
  def scalaVersion = "2.12.4"

  def ivyDeps = Agg(
    ivy"com.bot4s::telegram-core:4.0.0-RC1", // core
    ivy"com.bot4s::telegram-akka:4.0.0-RC1"  // extra goodies
  )
}

