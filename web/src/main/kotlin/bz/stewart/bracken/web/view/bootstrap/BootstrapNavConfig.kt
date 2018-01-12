package bz.stewart.bracken.web.view.bootstrap

import bz.stewart.bracken.web.service.WebPageContext

class BootstrapNavConfig {
    fun getNavs(context:WebPageContext): List<NavItem> {
        val navsTypes = listOf(BootStrapNavTypes.HOME, BootStrapNavTypes.BROWSE, BootStrapNavTypes.FIND_A_BILL)
        return navsTypes.map { StdNavItem(it.label, it.href, context.service == it.service) }.toList()
    }
}