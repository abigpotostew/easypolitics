package bz.stew.bracken.model.parse.bill

import bz.stew.bracken.ui.extension.html.jsParseDate
import bz.stew.bracken.ui.model.parse.bill.BillDataBuilder
import bz.stew.bracken.ui.model.parse.bill.EasyPoliticsMajorAction
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stew.bracken.ui.model.types.bill.status.BillStatusData
import bz.stewart.bracken.shared.data.*
import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.data.person.LegislatorRole

@Suppress("UnsafeCastFromDynamic")
/**
 * Created by stew on 4/29/17.
 */
class EasyPoliticsBillData : BillDataBuilder {

   private fun resolveMajorActions(actionsArr: dynamic): Collection<EasyPoliticsMajorAction> {
      val out = mutableListOf<EasyPoliticsMajorAction>()
      val numActions: Int = actionsArr
      for (i in 0..numActions) {
         val action: dynamic = actionsArr

         try {
            val datetime: String = action["acted_at"]
            val actionType: String = action["type"]
            val textDescription = action["text"]
            val actionObj = EasyPoliticsMajorAction(textDescription, actionType, datetime
                                                   )
            out.add(actionObj)
         } catch (e: Exception) {
            error("error parsing this action: ${e.toString()}")
         }
      }
      return out
   }

   override fun build(govInput: dynamic): BillData {
      val gi = govInput
      val title = gi.officialTitle
      val uniqueParsedId: String = gi.billId
      val uniqueId: Int = uniqueParsedId.hashCode()
      val congress: Int = gi.congress

      //val btVals = BillType.values()
      val bill_type: BillType = defaultBillTypeMatcher(gi.billType)
      val bill_res_type = matchVisibleType(BillResolutionType.values(), gi.resolutionType, VisibleTypeMatcher.LOWER)

      val majorActions = resolveMajorActions(gi.actionsArr)
      val resolvedFixedStatus = FixedStatus.valueOfDb(gi.currentStatus)

      //todo ststus label and description not entered

      val currentStatus = BillStatusData(fixedStatus = resolvedFixedStatus, date = jsParseDate(gi.currentStatusAt),
                                         majorActions = majorActions, description = gi.currentStatusDescription,
                                         label = gi.currentStatusLabel)

      val number = (gi.number as String).toInt()

      val link = gi.url
      val is_alive = true
      val is_current = true
      val intro_date = jsParseDate(gi.introducedAt) // todo this is just a number now
      val sponsor = Legislator(0, gi.sponsor.name, Party.DEMON, role = LegislatorRole.NONE, state = "DEMON",
                               twitterId = "The_Donald")//todo my data is just not here
      //val relatedBills

      return BillData(title = title, uniqueId = uniqueId, congress = congress, bill_type = bill_type,
                      bill_resolution_type = bill_res_type,
                      status = currentStatus, number = number, link = link, intro_date = intro_date, sponsor = sponsor,
                      origData = gi)
//      return BillData(
//            uniqueId = uniqueParsedId,
//            title=title,
//            congress = congress,
//            bill_type = bill_type,
//            bill_resolution_type = bill_resolution_type,
//            //display_number,
//            status = current_status,
//            number = number,
//            link = link,
//            is_alive = is_alive,
//            is_current = is_current,
//            intro_date = introduced_date,
//            sponsor = sponsor,
//            relatedBills =  relatedBills
//                     )
   }
}