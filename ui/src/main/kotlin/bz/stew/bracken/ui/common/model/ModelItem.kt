package bz.stew.bracken.ui.common.model

import bz.stew.bracken.ui.common.bill.status.BillStatus

/**
 * Created by stew on 1/25/17.
 */
interface ModelItem {

   /**
    * to identify this object in html
    */
   //fun uniqueId():Int
   /**
    * to identify this object in html
    */
   //fun typeName():String

   //fun lastMajorAction():MajorAction
   fun billStatus(): BillStatus

}