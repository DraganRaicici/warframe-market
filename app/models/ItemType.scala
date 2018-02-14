package models

object ItemType extends Enumeration {
  type ItemType = Value
  val MOD=Value("Mod")
  val WEAPON=Value("Weapon")
  val COMPONENT=Value("Component")
  val RELIC=Value("Relic")
}

