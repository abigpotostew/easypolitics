package bz.stewart.bracken.web.view.bootstrap

interface NavItem {
    val isActive: Boolean
    val href: String
    val label: String
    val classes: String?

}

class StdNavItem(override val label: String, override val href: String, isActive: Boolean, override val classes: String? = null) : NavItem {
    override val isActive: Boolean = isActive
}