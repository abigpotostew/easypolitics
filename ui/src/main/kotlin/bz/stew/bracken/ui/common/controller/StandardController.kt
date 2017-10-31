package bz.stew.bracken.ui.common.controller

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.model.ModelItem
import bz.stew.bracken.ui.common.query.BillRestQueryUrl
import bz.stew.bracken.ui.service.RequestCallback
import bz.stew.bracken.ui.service.ServerRequestDispatcher
import bz.stew.bracken.ui.service.Service
import bz.stew.bracken.ui.service.ServiceResponse
import bz.stew.bracken.view.View

/**
 * Created by stew on 1/23/17.
 */
abstract class StandardController<V:View, T:ModelItem>(override val view: V,
                                  override val model: Model<T>) : Controller<V,T> {

   protected var lastSuccessfulQuery: BillRestQueryUrl? = null
   protected var inProgressQuery: BillRestQueryUrl? = null

   abstract fun onParseError()

   fun loadEndpoint(service:Service<T>, requestUrl: BillRestQueryUrl, onDownload: (ServiceResponse<T>) -> Unit) {

   }
}
