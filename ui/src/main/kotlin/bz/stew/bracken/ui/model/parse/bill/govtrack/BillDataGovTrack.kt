package bz.stew.bracken.ui.model.parse.bill.govtrack

import bz.stew.bracken.ui.extension.html.jsDate
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.parse.bill.BillDataBuilder
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stew.bracken.ui.model.types.bill.BillProxy
import bz.stew.bracken.ui.model.types.bill.MajorAction
import bz.stew.bracken.ui.model.types.bill.RelatedBills
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.model.types.bill.status.BillStatusData
import bz.stew.bracken.ui.model.types.bill.status.emptyBillStatus
import bz.stew.bracken.ui.util.JsonUtil
import bz.stew.bracken.ui.util.log.Log
import bz.stewart.bracken.shared.data.BillResolutionType
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.VisibleType
import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.data.person.LegislatorRole
import bz.stewart.bracken.shared.data.person.emptyLegislator
import kotlin.js.Date

/**
 * Parse json data from govtrack
 * Created by stew on 1/23/17.
 */
class BillDataGovTrack(private val model: Model) : BillDataBuilder {

   var title: String = ""
   var uniqueParsedId: Int = -1
   var congress: Int = -1
   var bill_type: BillType = BillType.NONE
   var bill_type_label: String = ""
   var bill_resolution_type: BillResolutionType = BillResolutionType.NONE
   var display_number: Int = -1
   var current_status: BillStatus = emptyBillStatus()
   var number: Int = -1
   var link: String = ""
   var is_alive: Boolean = true
   var is_current: Boolean = true
   var sponsor: Legislator = emptyLegislator()
   var introduced_date: dynamic = Date()
   var majorActions: Collection<MajorAction> = mutableListOf()
   var relatedBills: RelatedBills = RelatedBills() // list of related bill proxy objects
   // H.R. 707 == house bill #707 == bill type label + number
   var displayNumber: String = "None"
   var cosponsors: MutableCollection<Int> = mutableListOf()

   val dateRegex: Regex = Regex("(\\d{4})-(\\d{2})-(\\d{2})")

   fun resolveType(values: Array<out VisibleType>,
                   matchToName: String): VisibleType {

      for (item: VisibleType in values) {
         val caps: String = item.capitalizedName()
         if (caps.equals(matchToName,
                         true)) {
            return item
         }
      }
      //println("Can't match type ${values[0]?.jsClass.name} with name = '$matchToName'")
      throw IllegalStateException("Can't match type ${values[0]::class} with name = '$matchToName'")
   }

   fun resolveMajorActions(actionArr: dynamic): Collection<GovTrackMajorAction> {
      //objects are arrays, with
      /**
       * 0= "datetime.datetime(2011, 1, 5, 14, 29)", //datetime.datetime(date yyyy,mm,dd,0,0) OR (yyyy,mm,dd,hh,mm,ss) (implicit -05:00 timezone)
      1= 6, //some kind of id for the action taken?
      2 = "On agreeing to the resolution Agreed to by voice vote.", //description of action
      3 = "<vote how=\"by voice vote\" type=\"vote\" dat..." // xml string of the same thing probably
       */

      val length: Int = actionArr.length
      if (length <= 0) {
         return emptyList()
      }
      val out = mutableListOf<GovTrackMajorAction>()
      for (i in 0..length - 1) {
         val action: dynamic = actionArr[i]

         try {
            val datetime = action[0]
            val id = action[1]
            val descr = action[2]
            val xml = action[3]
            val actionObj = GovTrackMajorAction(datetime as String,
                                                id,
                                                descr as String,
                                                xml as String
                                               )
            out.add(actionObj)
         } catch (e: Exception) {
            error("error parsing this action: ${e.toString()}")
         }
      }
      return out
   }

   fun parseDate(yyyymmdd: String): Date {
      val matches = dateRegex.findAll(yyyymmdd)
      var matchGroups: List<String> = matches.iterator().next().groupValues
      val year = matchGroups[1].toInt()
      val month = matchGroups[2].toInt() - 1
      val day = matchGroups[3].toInt()
      val d = jsDate(year, month, day)

      /*val d = jsDate()
      d.setFullYear(year)
      d.setMonth(month-1)
      d.setDate(day)
      d.setHours(0)
      d.setMinutes(0)
      d.setSeconds(0)
      d.setMilliseconds(0)*/

      //val d: Date = Date(year,month,day)

      return d
   }

   fun resolveRelatedBills(relatedArray: dynamic): RelatedBills {
      val length: Int = relatedArray.length
      if (length <= 0) {
         return RelatedBills()
      }
      val rltBills = RelatedBills()

      for (i in 0..length - 1) {
         try {
            val bill: Int = relatedArray[i].bill
            val relation: String = relatedArray[i].relation
            rltBills.add(relation, BillProxy(bill, this.model))
         } catch (ex: ClassCastException) {
            Log.warning("Could not create bill, bad data parsing")
         } catch (ex: NullPointerException) {
            Log.warning("Could not create bill: bill array out of index")
         }
      }
      return rltBills
   }

   fun parseCosponsors(relatedArray: dynamic): MutableCollection<Int> {
      val output = this.cosponsors
      output.clear()

      if (relatedArray == null) {
         return output
      }

      println(relatedArray)

      return output
   }

   override fun build(govInput: dynamic): BillData {
      val gi = govInput
      this.title = gi.title_without_number
      this.uniqueParsedId = gi.id

      val existingBill = model.getBillById(this.uniqueParsedId)
      if (existingBill != null) {
         return existingBill
      }

      this.congress = gi.congress //"number"
      this.bill_type = //resolveBillType(JsonUtil.niceString(gi.bill_type)) //"senate_bill", house_bill, house_resolution
            resolveType(BillType.values(),
                        JsonUtil.niceString(gi.bill_type)) as BillType
      //now linked to bill_type.shortLabel()
      //this.bill_type_label = gi.bill_type_label //[ S., H.R., H.Res.
      bill_resolution_type = gi.bill_resolution_type //"bill", ,"resolution"
      display_number = gi.display_number

      majorActions = resolveMajorActions(gi.major_actions) //array with .length
      current_status = BillStatusData(FixedStatus.valueOf(gi.current_status),
                                      Date(),
                                      gi.current_status_description,
                                      gi.current_status_label,
                                      majorActions)
      number = gi.number
      link = gi.link //string website
      is_alive = gi.is_alive
      is_current = gi.is_current
      introduced_date = parseDate(gi.introduced_date)
      //TODO make a legislator pool to get existing legislators
      val party: Party = resolveType(Party.values(),
                                     JsonUtil.niceString(gi.sponsor_role.party)) as Party
      val sponsorRole: LegislatorRole = resolveType(LegislatorRole.values(),
                                                    JsonUtil.niceString(gi.sponsor_role.role_type)) as LegislatorRole
      /*sponsor = Legislator(gi.sponsor.id,
                           gi.sponsor.name,
                           party,
                           sponsorRole,
                           gi.sponsor_role.state,
                           gi.sponsor.twitterid)*/

      relatedBills = resolveRelatedBills(gi.related_bills)

      cosponsors = parseCosponsors(gi.cosponsors)//array

      return BillData(
            uniqueId = uniqueParsedId,
            officialTitle = title,
            congress = congress,
            bill_type = bill_type,
            bill_resolution_type = bill_resolution_type,
            //display_number,
            status = current_status,
            number = number,
            link = link,
            is_alive = is_alive,
            is_current = is_current,
            intro_date = introduced_date,
            sponsor = sponsor,
            relatedBills = relatedBills
                     )
   }

}