package bz.stewart.bracken.web.view.bill

import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.BootstrapPageConfig
import kotlinx.html.SCRIPT

class SingleBillConfig(val billId: String) : BootstrapPageConfig() {

   override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return emptySet()//setOf(UiApiActions().doSingleBillAction(billId))
    }
}