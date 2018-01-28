package bz.stew.bracken.ui.api

import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.extension.Group
import bz.stew.bracken.ui.extension.Tool
import bz.stew.bracken.ui.extension.paper
import bz.stew.bracken.view.HtmlSelector
import kotlin.browser.window

/**
 * Entry point to browsing bills
 */
@Suppress("unused")
class BrowseMapRuntime : RuntimeUi {
    override fun execute(context: PageContext) {
        console.log("Hello from map UI.")
        val rootElement = HtmlSelector(Identifier.ID, "root")

        //val canvas = HtmlSelector(Identifier.ID, "mapCanvas").getElements()[0]

        paper.install(window)
        paper.setup("mapCanvas")
        paper.project.importSVG("/img/usaHigh.svg") //returned item is null, not sure why
        val tool = Tool()
        tool.onMouseMove = {

            paper.project.activeLayer.selected = false
            if(it.item != null) {
                it.item!!.selected = true
            }
        }
        paper.view.draw()

        val item = paper.project.activeLayer.firstChild
        for (child in item.children){
            paper.project.activeLayer.addChild(child)
        }
        paper.project.activeLayer.removeChildren(item.id)





    }
}