package models

case class User(id: Option[Long], name: String, password:String, availability: String = "Offline") {
}
