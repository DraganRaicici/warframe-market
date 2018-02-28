package models

case class User(id: Option[Long], name: String, availability: String = "Offline") {
}
