package bz.stew.bracken.ui.model.types.bill.status

import bz.stew.bracken.ui.extension.html.emptyDate
import bz.stew.bracken.ui.model.types.bill.MajorAction
import bz.stew.bracken.ui.model.types.bill.emptyMajorAction
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import kotlin.js.Date

/**
 * Created by stew on 3/4/17.
 */
class BillStatusData(private val fixedStatus: FixedStatus = FixedStatus.NONE,
                     private val date: Date = emptyDate(),
                     private val description: String = "",
                     private val label: String = "",
                     private val majorActions: Collection<MajorAction> = emptyList()) : BillStatus {

   private val lastMajorActionCached: MajorAction =
         this.majorActions.maxBy {
            val dt = it.date()
            dt?.getTime()
         } ?: emptyMajorAction()

   override fun lastMajorAction(): MajorAction {
      return this.lastMajorActionCached
   }

   override fun lastMajorStatus(): MajorStatus {
      return this.fixedStatus.lastMajorCompletedStatus
   }

   override fun fixedStatus(): FixedStatus {
      return this.fixedStatus
   }

   override fun date(): Date {
      return this.date
   }

   override fun description(): String {
      return this.description
   }

   override fun label(): String {
      return this.label
   }

   override fun majorActions(): Collection<MajorAction> {
      return this.majorActions
   }

}

val EMPTY_BILL_STATUS: BillStatus = BillStatusData(majorActions = listOf(emptyMajorAction()))

fun emptyBillStatus(): BillStatus {
   return EMPTY_BILL_STATUS
}

