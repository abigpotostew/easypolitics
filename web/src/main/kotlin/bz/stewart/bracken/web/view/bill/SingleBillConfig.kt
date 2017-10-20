package bz.stewart.bracken.web.view.bill

import bz.stewart.bracken.web.UiApiActions
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.MainPageConfig
import kotlinx.html.SCRIPT

class SingleBillConfig(val billId:String) :MainPageConfig(){
    override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return setOf(UiApiActions().doSingleBillAction(billId))+super.getEndBodyScripts()
    }
}