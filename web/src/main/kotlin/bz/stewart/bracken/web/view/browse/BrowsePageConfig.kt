package bz.stewart.bracken.web.view.browse

import bz.stewart.bracken.web.UiApiActions
import bz.stewart.bracken.web.html.config.TagConfiguration
import bz.stewart.bracken.web.view.MainPageConfig
import kotlinx.html.SCRIPT

class BrowsePageConfig : MainPageConfig() {
    override fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>> {
        return setOf(UiApiActions().doBrowseAction()) + super.getEndBodyScripts()
    }
}