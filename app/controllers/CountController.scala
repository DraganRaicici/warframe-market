package controllers

import javax.inject._

import repositories.ItemRepository
import play.api.mvc._
import services.Counter
import views.html
import play.api.i18n.I18nSupport

import scala.concurrent.ExecutionContext

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class creates an
 * `Action` that shows an incrementing count to users. The [[Counter]]
 * object is injected by the Guice dependency injection system.
 */
@Singleton
class CountController @Inject() (cc: ControllerComponents,
                                 itemRepo:ItemRepository,
                                 counter: Counter)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {

  import ItemForm._

  /**
   * Create an action that responds with the [[Counter]]'s current
   * count. The result is plain text. This `Action` is mapped to
   * `GET /count` requests by an entry in the `routes` config file.
   */
  def count = Action { Ok(counter.nextCount().toString) }

//  def item = Action.async { implicit request: MessagesRequest[AnyContent] =>
//    val errorFunction = { formWithErrors: Form[Data] =>
//      // This is the bad case, where the form had validation errors.
//      // Let's show the user the form again, with the errors highlighted.
//      // Note how we pass the form with errors to the template.
//      BadRequest(views.html.item(null, formWithErrors))
//    }
//
//    val successFunction = { data: Data =>
//      // This is the good case, where the form was successfully parsed as a Data object.
//      val found = itemRepo.findByName(data.name).map { item =>
//        Redirect(routes.CountController.item(item, successFunction))
//        Ok(views.html.item(item.get, successFunction))
//      }
//    }
//    val formValidationResult = form.bindFromRequest
//    formValidationResult.fold(errorFunction, successFunction)
//  }

//  def item() = Action.async { implicit rs =>
//    itemForm.bindFromRequest.fold(
//      formWithErrors => itemRepo.findByName(itemForm.get.name).map(item => BadRequest(html.item(item.get, formWithErrors))),
//      item => {
//        for {
//          _ <- itemRepo.findByName(item.name)
//        } yield Redirect(routes.CountController.item())
//      }
//    )
//  }

}
