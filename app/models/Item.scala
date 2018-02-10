package models

import java.time.ZonedDateTime

case class Item(val name: String, itemType: ItemType, price: Int, count: Int, publishedDate: ZonedDateTime) {
}
