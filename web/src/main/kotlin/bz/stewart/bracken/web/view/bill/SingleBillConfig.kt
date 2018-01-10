package bz.stewart.bracken.web.view.bill

import bz.stewart.bracken.web.RequireJsDataMain
import bz.stewart.bracken.web.html.config.ScriptDataMainConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.MainPageConfig
import kotlinx.html.SCRIPT

class SingleBillConfig(val billId: String) : MainPageConfig() {

   override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return emptySet()//setOf(UiApiActions().doSingleBillAction(billId))
    }
}