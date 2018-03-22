package repositories

import javax.inject.{Inject, Singleton}

import models.{Item, ItemType}
import models.ItemType._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.MySQLProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ItemRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[MySQLProfile] {

  import profile.api._

  private class ItemTable(tag: Tag) extends Table[Item](tag, "item") {

    implicit val itemTypeMapper = MappedColumnType.base[ItemType, String](
      e => e.toString,
      s => ItemType.withName(s)
    )

    def id = column[Long]("item_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def itemType = column[ItemType]("item_type")

    def description = column[String]("description")

    def link = column[String]("link")

    def * = (id.?, name, itemType, description, link) <> ((Item.apply _).tupled, Item.unapply)
  }

  private val items = TableQuery[ItemTable]

  def findById(id: Long): Future[Option[Item]] =
    db.run(items.filter(_.id === id).result.headOption)

  def findByName(name: String): Future[Option[Item]] =
    db.run(items.filter(_.name === name).result.headOption)

  def returnHardCodedItem(name: String): Item = {
    new Item(Some(2L), name, ItemType.Component, "empty desc", "empty link")
  }

  def list: Future[Seq[Item]] =
    db.run(items.result)

}

