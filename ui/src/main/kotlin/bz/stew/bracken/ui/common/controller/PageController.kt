package bz.stew.bracken.ui.common.controller

import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.model.ModelItem
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.pages.browse.view.mixins.BillOverview
import bz.stew.bracken.view.View

/**
 * Top level page controller. should be one per document
 */
abstract class PageController<V : View, T : ModelItem>(override val view: V,
                                                       override val model: Model<T>,
                                                       override val context: PageContext) : Controller<V, T>{
    abstract fun init(callback:() -> Unit)
}
