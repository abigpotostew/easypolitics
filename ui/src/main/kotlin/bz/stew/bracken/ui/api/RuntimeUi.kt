package bz.stew.bracken.ui.api

import bz.stew.bracken.ui.context.PageContext

interface RuntimeUi {
    fun execute(context: PageContext)
}