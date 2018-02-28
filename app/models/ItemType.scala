package models

object ItemType extends Enumeration {
  type ItemType = Value

  val Mod = Value("Mod")
  val Weapon = Value("Weapon")
  val Component = Value("Component")
  val Relic = Value("Relic")
}



