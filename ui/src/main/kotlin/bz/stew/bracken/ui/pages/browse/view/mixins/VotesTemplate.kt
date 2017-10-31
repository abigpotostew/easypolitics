package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.common.view.Classes
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stew.bracken.ui.pages.browse.view.Bill
import kotlinx.html.FlowContent
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr

/**
 * Created by stew on 7/4/17.
 */
class VotesTemplate(private val bill: Bill) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val numRows = 10
        val numColumns = 10
        root.table{
            ac(Classes.voteTable)
            for (row in 0..numRows) {
                tr {
                    for (col in 0..numColumns) {
                        td{
                            ac(Classes.voteCell)
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