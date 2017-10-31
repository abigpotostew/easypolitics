package bz.stew.bracken.ui.common.controller

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.query.BillRestQuery
import bz.stew.bracken.ui.service.ServiceResponse
import bz.stew.bracken.view.View

interface Controller<V : View> {
    val model: Model
    val view: V
    fun loadEndpoint(requestUrl: BillRestQuery, onDownload: (ServiceResponse<V>) -> Unit)
}