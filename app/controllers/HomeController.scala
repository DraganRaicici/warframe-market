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

  //  def item = Action.async { implicit request =>
  //    itemForm.bindFromRequest.fold(
  //      formWithErrors => itemRepo.findByName("Energy Siphon").map(item => BadRequest(views.html.index(formWithErrors))),
  //      item => {
  //        for {
  //          _ <- itemRepo.findByName(item.name)
  //        } yield Redirect(routes.HomeController.item(item,itemForm))
  //      }
  //    )
  //  }

  //    def item = Action.async { implicit request =>
  //      val errorFunction = { formWithErrors: Form[Data] =>
  //        // This is the bad case, where the form had validation errors.
  //        // Let's show the user the form again, with the errors highlighted.
  //        // Note how we pass the form with errors to the template.
  //        BadRequest(views.html.index(formWithErrors))
  //      }
  //
  //      val successFunction = { data: Data =>
  //        // This is the good case, where the form was successfully parsed as a Data object.
  //        val found = itemRepo.returnHardCodedItem
  //         // Redirect(routes.HomeController.item(found, successFunction))
  //          Ok(views.html.item(found, successFunction))
  //
  //      }
  //
  //      val formValidationResult = form.bindFromRequest
  //      formValidationResult.fold(errorFunction, successFunction)
  //    }

  //  def item = Action.async { implicit request =>
  //    itemForm.bindFromRequest.fold(
  //      formWithErrors => {
  //        BadRequest(views.html.index(formWithErrors))
  //      },
  //      toBeFound => {
  //        //val returned = itemRepo.returnHardCodedItem(toBeFound.name)
  //        val returned = itemRepo.findByName(toBeFound.name).map{
  //          case Some(m: Item)=> Ok(views.html.item(m, itemForm))
  //          case None=> NotFound
  //        }
  //        returned
  //        //Ok(views.html.item(returned, itemForm))
  //      }
  //    )
  //  }
  val item = Action.async(parse.form(itemForm, onErrors = (formWithErrors: Form[ItemForm.Data]) => {
    implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))
    BadRequest(views.html.index(formWithErrors))
  })) { implicit request =>
    val itemData = request.body
    val returned = itemRepo.findByName(itemData.name).map {
      case Some(m: Item) => Ok(views.html.item(m, itemForm))
      case None =>  NotFound(views.html.errors(itemData.name)) //BadRequest(views.html.index).flashing("notFoundError"->s"Item ${itemData.name} does not exist.")
    }
    returned
  }


  def hardCoded = Action { implicit request =>
    itemForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.index(formWithErrors)),
      item => Ok(s"Item ${item.name} was found"))
  }

  //  def find = Action.async { implicit request =>
  //    itemDao.findByName("Dead Eye").map{ item =>
  //      Ok(views.html.welcome(item.getOrElse("Not found").toString))
  //    }
  //  }

}
