package bz.stew.bracken.ui.common.controller

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.model.ModelItem
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.view.View

interface Controller<V : View, T : ModelItem> {
    val context:PageContext
    val model: Model<T>
    val view: V
}