package bz.stew.bracken.ui.pages.browse.controller

import bz.stew.bracken.ui.common.index.IndexEnum
import bz.stew.bracken.ui.common.bill.BillData
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.view.HtmlSelector
import bz.stewart.bracken.shared.data.party.Party

/**
 *
 * Created by stew on 2/6/17.
 */
enum class BillFilters(public val idxDef: IndexEnum,
                       val predicate: (BillData, Any?) -> Boolean = ::identity) {
   /**********************************************************
    * These names are liked to HTML, be careful editing them
    ********************************************************/
   IDENTITY(IndexEnum.NONE, ::identity),
   PARTY(IndexEnum.NONE, ::partyPredicate),
   FIXEDSTATUS(IndexEnum.FixedStatusIndex,
               ::statusPredicate),
   DATEINTROSTART(IndexEnum.IntroDate),
   DATEINTROEND(IndexEnum.IntroDate),
   LASTUPDATEDDATESTART(IndexEnum.LastUpdatedDate),
   LASTUPDATEDDATEEND(IndexEnum.LastUpdatedDate),
   LASTMAJORSTATUS(IndexEnum.LastMajorStatus),
   ;

   //private val lowerCase :String = this.name.toLowerCase()
   private val identifierCached = HtmlSelector(
         Identifier.ID, "billFilter-" + this.name.toLowerCase()
                                              )

   /**
    * TODO make this linked to HTML id
    */
   fun htmlSelector(): HtmlSelector {
      return identifierCached
   }
}

@Suppress("UNUSED_PARAMETER")
private fun identity(a: Any?,
                     b: Any?): Boolean {
   return false
}

private fun partyPredicate(bill: BillData,
                           selectVal: Any?): Boolean {
   val party: Party = selectVal as Party
   if (party.equals(Party.NONE)) {
      return true
   }
   return bill.sponsor.getParty().equals(party)
}

private fun statusPredicate(bill: BillData,
                            selectVal: Any?): Boolean {
   val fs: FixedStatus = selectVal as FixedStatus
   if (fs.equals(FixedStatus.NONE)) {
      return true
   }
   return bill.currentStatus.fixedStatus().equals(fs)
}