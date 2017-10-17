package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.table
import bz.stew.bracken.ui.extension.kotlinx.td
import bz.stew.bracken.ui.extension.kotlinx.tr
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.FlowContent

/**
 * Created by stew on 7/4/17.
 */
class VotesTemplate(private val bill: Bill) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val numRows = 10
        val numColumns = 10
        root.table(Classes.voteTable) {
            for (row in 0..numRows) {
                tr {
                    for (col in 0..numColumns) {
                        td(Classes.voteCell) {
                            val n = row * numColumns + col
                            if ((n + row) % 2 == 0) {
                                ac(Classes.voteDem)
                            } else {
                                ac(Classes.voteRep)
                            }
                            +"$n"
                        }
                    }
                }
            }
        }
    }
}