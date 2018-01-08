package bz.stew.bracken.ui.api

import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.pages.singlebill.controller.SingleBillController
import bz.stew.bracken.view.HtmlSelector

/**
 * Entry point to viewing a single bill
 */
@Suppress("unused")
class SingleBillRuntime(private val billId: String) : RuntimeUi {
    override fun execute(context: PageContext) {
        val rootElement = HtmlSelector(Identifier.ID, "root")
        val controller = SingleBillController(rootElement, this.billId, context)
        controller.view.setLoading(true)

        controller.init({
            println("done loading bill with id: ${this.billId}")
            controller.view.setLoading(false)
        })
    }
}