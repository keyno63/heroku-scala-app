package jp.co.keyno.sandbox.sample.domain.config

import com.typesafe.config.ConfigFactory

class Config {
  private val config = ConfigFactory.load()

  val system: ServerConfig = ServerConfig(
    config.getInt("app.server.port")
  )
  val db: DbConfig = DbConfig(
    config.getString("app.db.driver"),
    config.getString("app.db.url"),
    config.getString("app.db.user"),
    config.getString("app.db.password")
  )
}

case class ServerConfig(
  port: Int
)

case class DbConfig(
  driverName: String,
  url: String,
  user: String,
  password: String,
)
