package bz.stew.bracken.ui.model.types.bill.status

import bz.stew.bracken.ui.model.types.bill.MajorAction
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import kotlin.js.Date

/**
 * Created by stew on 1/23/17.
 */
interface BillStatus {

    fun fixedStatus(): FixedStatus
    fun date(): Date
    fun description(): String
    fun label(): String
    fun majorActions(): Collection<MajorAction>
    fun lastMajorAction(): MajorAction
    fun lastMajorStatus(): MajorStatus
}
//
//val ebs = object:BillStatus{
//    override fun fixedStatus(): FixedStatus {
//        return FixedStatus.NONE
//    }
//    override fun date(): Date {
//        return Date()
//    }
//    override fun description(): String {
//        return ""
//    }
//    override fun label(): String {
//        return ""
//    }
//    override fun majorActions(): Collection<MajorAction> {
//        return emptyList()
//    }
//    override fun lastMajorAction(): MajorAction {
//        return emptyMajorAction()
//    }
//    override fun lastMajorStatus(): MajorStatus {
//        return MajorStatus.NONE
//    }
//}
//fun emptyBillStatus():BillStatus{
//    return ebs
//}