package bz.stew.bracken.ui.common.view.mixins

import bz.stewart.bracken.shared.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.extension.kotlinx.ac
import kotlinx.html.FlowContent
import kotlinx.html.div

class RowColContentMixin(private val content:SubTemplate): SubTemplate{
    override fun renderIn(root: FlowContent) {
        root.div {
            ac(Classes.boots_row)
            div {
                ac(Classes.boots_col)

            }
        }
    }
}