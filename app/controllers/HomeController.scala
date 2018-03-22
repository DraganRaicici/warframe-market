package controllers

import javax.inject._

import controllers.ItemForm.itemForm
import models.Item
import play.api.i18n.{I18nSupport, Lang}
import play.api.mvc._
import repositories.ItemRepository
import play.api.data.Form

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(
                                itemRepo: ItemRepository,
                                cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action { implicit request =>
    Ok(views.html.index(itemForm))
  }

  import ItemForm._

  val item = Action.async(parse.form(itemForm, onErrors = (formWithErrors: Form[ItemForm.Data]) => {
    implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))
    BadRequest(views.html.index(formWithErrors))
  })) { implicit request =>
    val itemData = request.body
    val returned = itemRepo.findByName(itemData.name).map {
      case Some(m: Item) => Ok(views.html.item(m, itemForm))
      case None =>  NotFound(views.html.errors(itemForm, itemData.name))
    }
    returned
  }
}
