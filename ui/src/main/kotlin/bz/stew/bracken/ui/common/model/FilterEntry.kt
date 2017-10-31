package bz.stew.bracken.ui.common.model

import bz.stew.bracken.ui.pages.browse.controller.BillFilters

/**
 * Created by stew on 2/8/17.
 */
interface FilterEntry {
    @Deprecated("Don't use this")
    fun filterType(): BillFilters
}