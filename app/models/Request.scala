package models

import java.time.ZonedDateTime

case class Request(user: User, requestType: RequestType.RequestType, item: Item, price: Int, count: Int, publishedDate: ZonedDateTime) {

}
