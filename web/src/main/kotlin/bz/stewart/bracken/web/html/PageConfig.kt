package bz.stewart.bracken.web.html

import bz.stewart.bracken.web.html.config.TagConfiguration
import kotlinx.html.LINK
import kotlinx.html.META
import kotlinx.html.SCRIPT
import kotlinx.html.TITLE

interface PageConfig {
    fun getMetas(): Set<TagConfiguration<META>>
    fun getLinks(): Set<TagConfiguration<LINK>>
    fun getBeginBodyScripts(): Set<TagConfiguration<SCRIPT>>
    fun getNavHeader(): ViewRender?
    fun getTitle(): TagConfiguration<TITLE>
    fun getEndBodyScripts(): Set<TagConfiguration<SCRIPT>>
}