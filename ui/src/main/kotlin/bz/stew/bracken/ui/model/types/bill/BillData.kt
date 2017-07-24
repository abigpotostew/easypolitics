package bz.stew.bracken.ui.model.types.bill

import bz.stew.bracken.ui.model.ModelItem
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.model.types.bill.status.emptyBillStatus
import bz.stewart.bracken.shared.data.BillResolutionType
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.data.person.emptyLegislator
import kotlin.js.Date

/**
 * This contains data for a unique bill. this should be pooled in future, but needs to maintain updated status
 * Created by stew on 1/23/17.
 */
data class BillData(val uniqueId: Int = -1,
                    val officialTitle: String = "",
                    val shortTitle: String = "",
                    val congress: Int = -1,
                    val bill_type: BillType = BillType.NONE,
                    val bill_resolution_type: BillResolutionType = BillResolutionType.NONE,
                    val currentStatus: BillStatus = emptyBillStatus(),
                    val number: Int = -1,
                    val link: String = "",
                    val is_alive: Boolean = false,
                    val is_current: Boolean = false,
                    val intro_date: Date = Date(),
                    val sponsor: Legislator = emptyLegislator(),
                    val relatedBills: RelatedBills = RelatedBills(),
                    val cosponsors: List<Legislator> = emptyList(),
                    val origData: dynamic = null) : ModelItem {

   override fun billStatus(): BillStatus {
      return this.currentStatus
   }

//    override fun lastMajorAction(): MajorAction {
//        return this.status.lastMajorAction()
//    }
//
//    fun lastMajorStatus():MajorStatus{
//        return this.status.lastMajorStatus()
//    }

   fun lastUpdated(): Double {
      return this.currentStatus.lastMajorAction().date().getTime()
   }

   fun lastUpdatedDate(): Date {
      return this.currentStatus.date() ?: this.currentStatus.lastMajorAction().date()
   }

   override fun toString(): String {
      return "BillData\$${uniqueId}@${intro_date.getTime()}"
   }
}