package bz.stew.bracken.ui.controller


import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.view.View

/**
 * Created by stew on 1/23/17.
 */
abstract class Controller(val view: View,
                          val model: Model) {

   abstract fun onParseError()

   //abstract fun loadData(dataRequest: DataRequest)


}