package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.BootstrapPageConfig
import kotlinx.html.SCRIPT

class BrowsePageConfig : BootstrapPageConfig() {

   override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return emptySet()//setOf(UiApiActions().doSingleBillAction(billId))
    }

}