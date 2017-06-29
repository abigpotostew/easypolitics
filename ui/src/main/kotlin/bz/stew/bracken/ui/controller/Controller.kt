package bz.stew.bracken.ui.controller

import bz.stew.bracken.ui.controller.bill.query.BillRestQuery
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.service.ServiceResponse
import bz.stew.bracken.view.View

interface Controller {
   val model: Model
   val view: View
   fun loadEndpoint(requestUrl: BillRestQuery, onDownload: (ServiceResponse) -> Unit)
}