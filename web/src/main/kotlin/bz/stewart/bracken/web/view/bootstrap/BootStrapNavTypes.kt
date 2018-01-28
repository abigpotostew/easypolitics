package bz.stewart.bracken.web.view.bootstrap

import bz.stewart.bracken.shared.web.AppServices

enum class BootStrapNavTypes(label:String, val service:AppServices, extraClasses:String?=null):NavItem {

    HOME("Home", AppServices.MAIN),
    BROWSE("Browse", AppServices.BROWSE_BILL),
    FIND_A_BILL("Find a Bill", AppServices.SEARCH),
    MAP("Map", AppServices.MAP),
    ;

    override val href: String = this.service.absoluteUrlPath
    override val label: String = label
    override val isActive: Boolean = false
    override val classes: String? = extraClasses
}