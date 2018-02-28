package models

import models.ItemType._


case class Item(id: Option[Long], name: String, itemType: ItemType, description: String, link: String) {
}
