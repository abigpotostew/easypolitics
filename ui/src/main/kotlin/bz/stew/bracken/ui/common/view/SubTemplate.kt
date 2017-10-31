package bz.stew.bracken.ui.common.view

import kotlinx.html.FlowContent

/**
 * Created by stew on 7/4/17.
 */
interface SubTemplate {
    fun renderIn(root: FlowContent)
}