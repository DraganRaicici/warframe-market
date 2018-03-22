package repositories

import javax.inject.{Inject, Singleton}

import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.MySQLProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[MySQLProfile] {

  import profile.api._

  private class UserTable(tag: Tag) extends Table[User](tag, "user") {

    def id = column[Long]("user_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def password = column[String]("pw")

    def availability = column[String]("availability")

    def * = (id.?, name, password, availability) <> ((User.apply _).tupled, User.unapply)
  }

  private val users = TableQuery[UserTable]

  def findById(id: Long): Future[Option[User]] =
    db.run(users.filter(_.id === id).result.headOption)

}
