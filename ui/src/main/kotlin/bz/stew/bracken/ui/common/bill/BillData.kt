package bz.stew.bracken.ui.common.bill

import bz.stew.bracken.ui.common.model.ModelItem
import bz.stew.bracken.ui.common.bill.status.BillStatus
import bz.stew.bracken.ui.common.bill.status.emptyBillStatus
import bz.stewart.bracken.shared.data.BillResolutionType
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.data.person.emptyLegislator
import kotlin.js.Date

/**
 * This contains data for a unique bill. this should be pooled in future, but needs to maintain updated status
 * Created by stew on 1/23/17.
 * have this inherit from PublicBill
 */
data class BillData(
    val billId:String,
      val officialTitle: String = "",
      val shortTitle: String = "", //is the bill id in the front of officialTitle
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
      val origData: dynamic = null,
      val subjectsTopTerm:String = "",
      val subjects: Set<BillSubject>,
      val actions:Set<BillAction> = emptySet()) : ModelItem {

    override fun billStatus(): BillStatus {
        return this.currentStatus
    }

    fun lastUpdated(): Double {
        return this.currentStatus.lastMajorAction().date().getTime()
    }

    fun lastUpdatedDate(): Date {
        return this.currentStatus.date()
    }

    override fun toString(): String {
        return "BillData[${this.billId}]@${this.intro_date.getTime()}"
    }

    fun officialId(useFullType:Boolean=false):String{
        val typeStr = if (useFullType) bill_type.niceFormat() else bill_type.shortLabel()
        return "$typeStr $number"
    }
}