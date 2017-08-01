package models

case class User(name: String, status: String = "Offline", messages: List[Message] = List[Message]()) {
}
