package bz.stewart.bracken.web.view.bootstrap

import bz.stewart.bracken.web.service.WebPageContext

class BootstrapNavConfig:NavConfigBuilder {
    override fun getNavs(context:WebPageContext): List<NavItem> {
        val navsTypes = listOf(BootStrapNavTypes.BROWSE, BootStrapNavTypes.FIND_A_BILL, BootStrapNavTypes.MAP)
        return navsTypes.map { StdNavItem(it.label, it.href, context.service == it.service) }.toList()
    }
}

interface NavConfigBuilder{
    fun getNavs(context:WebPageContext):List<NavItem>
}