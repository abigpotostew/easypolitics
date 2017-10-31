package bz.stew.bracken.ui.common.bill

import bz.stew.bracken.ui.extension.html.jsParseDate
import bz.stewart.bracken.shared.data.BillReference
import bz.stewart.bracken.shared.data.PublicAction
import kotlin.js.Date

/**
 * parse on demand this action. no undefined checks, do that before construction
 * this way of parsing is dank.
 */
class BillAction (private val data:dynamic): PublicAction {
    override fun getActedAt(): String = data.actedAt

    override fun getText(): String = data.text

    override fun getActionCode(): String = data.actionCode

    override fun getCommittess(): Array<String>? = data.committess

    override fun getNumber(): Int = data.number

    override fun getBillReferences(): Array<BillReference>? = data.billReferences

    override fun getBillIds(): Array<String>? = data.billIds

    fun actedAtDate():Date{
        val actedAt = data.actedAt
        return jsParseDate(actedAt)
    }
}