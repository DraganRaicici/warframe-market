package models

object RequestType extends Enumeration {
  type RequestType = Value
  val SELL = Value("Sell")
  val BUY = Value("Buy")

}
