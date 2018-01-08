package bz.stew.bracken.ui.service

import bz.stew.bracken.ui.context.PageContext

interface ServiceEndpoint {
    val context:PageContext
    fun getUrl(): String
}