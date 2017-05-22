package bz.stew.bracken.ui.model.parse.bill

import bz.stew.bracken.ui.model.types.bill.BillData

/**
 * Created by stew on 4/29/17.
 */
interface BillDataBuilder {
   fun build(billData: dynamic): BillData
}