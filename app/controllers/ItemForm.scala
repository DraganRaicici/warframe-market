package controllers

object ItemForm{
  import play.api.data.Form
  import play.api.data.Forms._



  /**
  * A form processing DTO that maps to the form below.
    *
  * Using a class specifically for form binding reduces the chances
  * of a parameter tampering attack and makes code clearer.
  */
  case class Data(name: String)

  /**
    * The form definition for the "search an item" form.
    * It specifies the form fields and their types,
    * as well as how to convert from a Data to form data and vice versa.
    */
  val itemForm = Form(
    mapping(
      "name" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

}