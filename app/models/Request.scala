package models

import java.time.ZonedDateTime

case class Request(id: Option[Long], user: User, requestType: RequestType.RequestType, item: Item, price: Int, count: Int, publishedDate: ZonedDateTime) {

}
