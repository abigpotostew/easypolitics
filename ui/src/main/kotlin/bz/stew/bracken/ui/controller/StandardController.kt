package bz.stew.bracken.ui.controller

import bz.stew.bracken.ui.controller.bill.query.BillRestQuery
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.service.RequestCallback
import bz.stew.bracken.ui.service.ServerRequestDispatcher
import bz.stew.bracken.ui.service.ServiceResponse
import bz.stew.bracken.view.View

/**
 * Created by stew on 1/23/17.
 */
abstract class StandardController<V:View>(override val view: V,
                                  override val model: Model) : Controller<V> {

   protected var lastSuccessfulQuery: BillRestQuery? = null
   protected var inProgressQuery: BillRestQuery? = null

   abstract fun onParseError()

   override fun loadEndpoint(requestUrl: BillRestQuery, onDownload: (ServiceResponse<V>) -> Unit) {
      inProgressQuery = requestUrl
      val controller = this
      ServerRequestDispatcher().sendRequest(
            requestUrl.toString(),
            object : RequestCallback() {
               override fun onLoad(response: String) {
                  lastSuccessfulQuery = inProgressQuery
                  inProgressQuery = null
                  onDownload(ServiceResponse(controller, response))
               }
            })
   }
}
