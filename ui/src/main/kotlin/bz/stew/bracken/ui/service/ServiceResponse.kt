package bz.stew.bracken.ui.service

import bz.stew.bracken.ui.controller.Controller
import bz.stew.bracken.view.View

/**
 * Created by stew on 6/28/17.
 */
data class ServiceResponse<V : View>(val controller: Controller<V>, val response: String)