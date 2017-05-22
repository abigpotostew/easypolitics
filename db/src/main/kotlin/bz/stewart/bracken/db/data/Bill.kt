package bz.stewart.bracken.db.data

import bz.stewart.bracken.db.database.DbItem
import bz.stewart.bracken.shared.DateUtils
import bz.stewart.bracken.shared.data.*
import bz.stewart.bracken.db.data.parse.DbDateSerializer
import bz.stewart.bracken.db.data.parse.FlexibleDateParser
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.types.ObjectId
import java.util.*

/**
 * Bill that is read from congress data and stored in database
 * Created by stew on 3/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bill(
      val _id: org.bson.types.ObjectId? = null,

      val bill_id: String = "", //[bill_type][number]-[congress]

      @JsonProperty("actions")
      val actionsArr: Array<Action> = emptyArray(),

      @JsonDeserialize(using = BillTypeDeserializer::class) @JsonSerialize(using = BillTypeSerializer::class)
      val bill_type: BillType = BillType.NONE,

      val by_request: String = "",

      val committee_reports: Array<String>? = null, //ignore, this is not documented

      @JsonProperty("congress")
      val congressNum: Int = -1,

      @JsonProperty("cosponsors")
      val cosponsorsArr: Array<Sponsor> = emptyArray(),

      val enacted_as: EnactedAs? = null,

      @JsonProperty("history")
      val billHistory: BillHistory = BillHistory(),

      @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)
      val introduced_at: Date = DateUtils.defaultDate(),

      @JsonProperty("number")
      val billNumber: String = "",

      @JsonProperty("committees")
      @JsonIgnore
      val committeesArr: Array<Any> = emptyArray(),

      val official_title: String = "",

      val popular_title: String? = null,

      val related_bills: Array<RelatedBill> = emptyArray(),

      val short_title: String? = null,

      @JsonProperty("sponsor")
      val billSponsor: Sponsor = Sponsor(),

      @JsonProperty("status")
      val currentStatus: String = "",

      @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)
      val status_at: Date = DateUtils.defaultDate(),

      @JsonProperty("subjects")
      val subjectsArr: Array<String> = emptyArray(),

      val subjects_top_term: String? = null, //top descriptor tag for this bill

      @JsonProperty("summary")
      val billSummary: Summary? = null,

      @JsonProperty("titles")
      val titlesArr: Array<Title> = emptyArray(),

      @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)
      val updated_at: Date = DateUtils.defaultDate(),

      @JsonProperty("url")
      val urlBill: String = ""
      //@JsonIgnore val lastModifiedString: Date? = DateUtils.defaultDate()
               ) : DbItem { //use PublicBillDelagated for the public wrapper. this is just the data container


   //todo, or do I??
   @JsonDeserialize(using = FlexibleDateParser::class) @JsonSerialize(using = DbDateSerializer::class)
   private var lastModifiedDate: Date? = null//DateUtils.parseModifiedOrUpdatedStrings (lastModifiedString, updated_at) ?: DateUtils.defaultDate()

   //private var _amendments: Any? = null
   var amendments: Any?
      get() = null
      @JsonIgnore set(value) {}


   /*override fun getBillId(): String? {
      return this.bill_id
   }

   //@JsonIgnore
   override fun getActions(): Array<Action>? {
      return this.actionsArr
   }

   override fun getBillType(): String? {
      return this.bill_type
   }

   override fun getByRequest(): String? {
      return this.by_request
   }

   override fun getCommitteeReports(): Array<String>? {
      return this.committee_reports
   }

   //@JsonIgnore
   override fun getCongress(): String? {
      return this.congressNum
   }

   //@JsonIgnore
   override fun getCosponsors(): Array<Sponsor> {
      return this.cosponsorsArr
   }

   override fun getEnactedAs(): Document? {
      return this.enacted_as
   }

   //@JsonIgnore
   override fun getHistory(): BillHistory {
      return this.billHistory
   }

   override fun getIntroducedAt(): Date {
      return this.introduced_at
   }

   //@JsonIgnore
   override fun getNumber(): String? {
      return this.billNumber
   }

   //@JsonIgnore
   override fun getCommittees(): Array<Document> {
      return this.committeesArr
   }

   override fun getOfficialTitle(): String {
      return this.official_title
   }

   override fun getPopularTitle(): String? {
      return this.popular_title
   }

   override fun getRelatedBills(): Array<RelatedBill> {
      return this.related_bills
   }

   override fun getShortTitle(): String? {
      return this.short_title
   }

   //@JsonIgnore
   override fun getSponsor(): Sponsor {
      return this.billSponsor
   }

   //@JsonIgnore
   override fun getCurrentStatus(): String {
      return this.currentStatus
   }

   override fun getStatusAt(): String {
      return this.status_at
   }

   //@JsonIgnore
   override fun getSubjects(): Array<String> {
      return this.subjectsArr
   }

   override fun getSubjectsTopTerm(): String? {
      return this.subjects_top_term
   }

   //@JsonIgnore
   override fun getSummary(): Document? {
      return this.billSummary
   }

   //@JsonIgnore
   override fun getTitles(): Array<Title> {
      return this.titlesArr
   }

   override fun getUpdatedAt(): Date {
      return this.updated_at
   }

   //@JsonIgnore
   override fun getUrl(): String {
      return this.urlBill
   }*/


   override fun getDbId(): ObjectId? {
      return this._id
   }

   override fun setLastModified(lastMod: Date?) {
      lastModifiedDate = lastMod
   }

   override fun getLastModified(): Date? {
      return lastModifiedDate
   }



//   override fun toString():String{
//      return bill_id
//   }


   override fun <T : DbItem> equalLessId(other: T): Boolean {


      if (this === other) return true
      if (other::class != this::class) return false
      if (this.getDbId() == other.getDbId()) return true

      other as Bill

      if (bill_id != other.bill_id) return false
      if (!Arrays.equals(actionsArr, other.actionsArr)) return false
      if (bill_type != other.bill_type) return false
      if (by_request != other.by_request) return false
      if (!Arrays.equals(committee_reports, other.committee_reports)) return false
      if (congressNum != other.congressNum) return false
      if (!Arrays.equals(cosponsorsArr, other.cosponsorsArr)) return false
      if (enacted_as != other.enacted_as) return false
      if (billHistory != other.billHistory) return false
      if (introduced_at != other.introduced_at) return false
      if (billNumber != other.billNumber) return false
      if (!Arrays.equals(committeesArr, other.committeesArr)) return false
      if (official_title != other.official_title) return false
      if (popular_title != other.popular_title) return false
      if (!Arrays.equals(related_bills, other.related_bills)) return false
      if (short_title != other.short_title) return false
      if (billSponsor != other.billSponsor) return false
      if (currentStatus != other.currentStatus) return false
      if (status_at != other.status_at) return false
      if (!Arrays.equals(subjectsArr, other.subjectsArr)) return false
      if (subjects_top_term != other.subjects_top_term) return false
      if (billSummary != other.billSummary) return false
      if (!Arrays.equals(titlesArr, other.titlesArr)) return false
      if (updated_at != other.updated_at) return false
      if (urlBill != other.urlBill) return false
      if (lastModifiedDate != other.lastModifiedDate) return false
      //if (lastModifiedDate != other.lastModifiedDate) return false

      return true

   }


}