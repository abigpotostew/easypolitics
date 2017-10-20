package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.config.BasicConfig
import bz.stewart.bracken.web.html.config.ExecuteJsScriptConfig
import bz.stewart.bracken.web.html.config.TagConfiguration
import kotlinx.html.SCRIPT

class UiApiActions {
    fun doBrowseAction(): TagConfiguration<SCRIPT> {
        val config = ExecuteJsScriptConfig("function EasypoliticsUiApiBrowse(){ return true; }")
        return config
    }
    fun doSingleBillAction(billId:String):TagConfiguration<SCRIPT>{
        return ExecuteJsScriptConfig("function EasypoliticsUiApiSingleBill() { return '$billId'; }")
    }
}