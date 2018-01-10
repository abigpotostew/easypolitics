package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.RequireJsDataMain
import bz.stewart.bracken.web.UiApiActions
import bz.stewart.bracken.web.html.config.ScriptDataMainConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.MainPageConfig
import kotlinx.html.SCRIPT

class BrowsePageConfig : MainPageConfig() {

   override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return emptySet()//setOf(UiApiActions().doSingleBillAction(billId))
    }

}