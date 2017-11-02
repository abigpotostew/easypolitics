package bz.stew.bracken.ui.api

import bz.stew.bracken.ui.pages.browse.controller.BrowseBillsController
import bz.stew.bracken.ui.common.query.BillRestQueryUrl
import bz.stew.bracken.ui.pages.browse.model.BillModelEasyPoliticsRest
import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.view.HtmlSelector

/**
 * Entry point to browsing bills
 */
@Suppress("unused")
class BrowseRuntime :RuntimeUi{
    override fun execute() {
        val rootElement = HtmlSelector(Identifier.ID,"root")
        val controller = BrowseBillsController(rootElmt = rootElement,
                requestUrl = BillRestQueryUrl(congress = 115, limit = 50),
              model = BillModelEasyPoliticsRest())
        controller.view.setLoading(true)

        controller.init {
                    controller.startupSetupUi()
                    controller.view.setLoading(false)
                    println("done loading")
                }
    }
}