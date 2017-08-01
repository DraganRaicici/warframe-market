package models

import java.time.ZonedDateTime

case class Message(recipient: User, sender: User, content: String, sent_date: ZonedDateTime = ZonedDateTime.now()) {
}

