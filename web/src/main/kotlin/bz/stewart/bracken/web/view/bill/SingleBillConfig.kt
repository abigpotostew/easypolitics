package bz.stewart.bracken.web.view.bill

import bz.stewart.bracken.web.RequireJsDataMain
import bz.stewart.bracken.web.ScriptSrcConstants
import bz.stewart.bracken.web.UiApiActions
import bz.stewart.bracken.web.html.config.ScriptConfig
import bz.stewart.bracken.web.html.config.ScriptDataMainConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.MainPageConfig
import kotlinx.html.SCRIPT

class SingleBillConfig(val billId:String) :MainPageConfig(){
    override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        //+super.getEndBodyScripts()//this is hella flaky
        return emptySet()//setOf(UiApiActions().doSingleBillAction(billId))
    }

    override fun getBeginBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return super.getBeginBodyScripts() + setOf(ScriptDataMainConfig(RequireJsDataMain.REQUIREJS_APP))
    }


}