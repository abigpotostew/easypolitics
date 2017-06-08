package bz.stewart.bracken.rest.bills

import bz.stewart.bracken.db.bill.data.BillHistory
import bz.stewart.bracken.db.bill.data.RelatedBill
import bz.stewart.bracken.db.bill.data.Summary
import bz.stewart.bracken.db.bill.data.Title
import bz.stewart.bracken.db.bill.data.parse.DbDateSerializer
import bz.stewart.bracken.shared.DateUtils
import bz.stewart.bracken.shared.data.*
import bz.stewart.bracken.shared.data.person.PublicLegislator
import java.util.*

/**
 * Created by stew on 4/1/17.
 */
val EMPTY_CONGRESS = -1

class BillExample(val bill_id: String? = null,
                  val actionsArr: Array<PublicAction>? = null,
                  val bill_type: BillType = BillType.NONE,
                  val by_request: String? = null,
                  val committee_reports: Array<String>? = null, //ignore = null, this is not documented
                  val congressNum: Int = EMPTY_CONGRESS,
                  val cosponsorsArr: Array<PublicSponsor>? = null,
                  val enacted_as: EnactedAs? = null,
                  val billHistory: BillHistory? = null,
                  val introduced_at: Date? = null,
                  val billNumber: String? = null,
                  val committeesArr: Array<Any>? = null,
                  val official_title: String? = null,
                  val popular_title: String? = null,
                  val related_bills: Array<RelatedBill>? = null,
                  val short_title: String? = null,
                  val billSponsor: PublicSponsor? = null,
                  val currentStatusString: String? = null,
                  val status_at: Date? = null,
                  val subjectsArr: Array<String>? = null,
                  val subjects_top_term: String? = null,
                  val billSummary: Summary? = null,
                  val titlesArr: Array<Title>? = null,
                  val updated_at: Date? = null,
                  val urlBill: String?=null)
   : PublicBill {
   override fun getCurrentStatusDescription(): String {
       return EMPTY_STRING
   }

   override fun getCurrentStatusLabel(): String {
      TODO()
   }

   override fun id(): Int {
      TODO()
   }

   override fun getResolutionType(): String {
      TODO()
   }

   private val EMPTY_STRING = ""

   override fun getBillName(): String {
      //return "$bill_type.shortName number"
      TODO()
   }

   override fun getBillId(): String {
      return bill_id ?: EMPTY_STRING
   }

   override fun getActions(): Array<PublicAction>? {
      return actionsArr
   }

   override fun getBillType(): String {
      return bill_type.shortCode()
   }

   override fun getByRequest(): String? {
      return by_request
   }

   override fun getCommitteeReports(): Array<String>? {
      return committee_reports
   }

   override fun getCongress(): Int {
      return congressNum
   }

   fun hasEmptyCongress():Boolean{
      return congressNum == EMPTY_CONGRESS
   }

   override fun getCosponsors(): Array<PublicLegislator>? {
      return emptyArray()
   }

   override fun getEnactedAs(): EnactedAs? {
      return enacted_as
   }

   override fun getHistory(): BillHistory? {
      return billHistory
   }

   override fun getIntroducedAt(): String? {
      return DbDateSerializer().serializeDate(introduced_at)
   }

   override fun getNumber(): String? {
      return billNumber
   }

   override fun getCommittees(): Array<Any>? {
      return committeesArr
   }

   override fun getOfficialTitle(): String? {
      return official_title
   }

   override fun getPopularTitle(): String? {
      return popular_title
   }

   //todo
   override fun getRelatedBills(): Array<PublicRelatedBill>? {
      return null//related_bills
   }

   override fun getShortTitle(): String? {
      return short_title
   }

   override fun getSponsor(): PublicLegislator? {
      return null
   }

   override fun getCurrentStatus(): FixedStatus {
      if(currentStatusString==null){
         return FixedStatus.NONE
      }
      return try{
         FixedStatus.valueOfDb(currentStatusString)
      }catch (e:IllegalArgumentException){
         FixedStatus.NONE
      }
   }

   override fun getCurrentStatusAt(): String {
      return DbDateSerializer().serializeDate(status_at ?: DateUtils.defaultDate())
   }

   override fun getSubjects(): Array<String>? {
      return subjectsArr
   }

   override fun getSubjectsTopTerm(): String? {
      return subjects_top_term
   }

   override fun getSummary(): Summary? {
      return billSummary
   }

   //todo
   override fun getTitles(): Array<PublicTitle>? {
      return null//titlesArr
   }

   override fun getUpdatedAt(): String? {
      return DbDateSerializer().serializeDate(updated_at)
   }

   override fun getUrl(): String? {
      return urlBill
   }

   //constructor(billNumber: String? = null, bill_id: String? = null, bill_type: String? = null, congressNum: String?) : this()
}