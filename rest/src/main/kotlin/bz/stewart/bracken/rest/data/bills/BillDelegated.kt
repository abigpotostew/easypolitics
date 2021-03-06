package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.db.bill.data.Bill
import bz.stewart.bracken.db.bill.data.parse.DbDateSerializer
import bz.stewart.bracken.db.leglislators.data.LegislatorData
import bz.stewart.bracken.rest.data.legislators.DelegatedLegislator
import bz.stewart.bracken.shared.data.EnactedAs
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.PublicAction
import bz.stewart.bracken.shared.data.PublicBill
import bz.stewart.bracken.shared.data.PublicBillHistory
import bz.stewart.bracken.shared.data.PublicRelatedBill
import bz.stewart.bracken.shared.data.PublicSummary
import bz.stewart.bracken.shared.data.PublicTitle
import bz.stewart.bracken.shared.data.person.PublicLegislator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * THIS IS THE DATA THAT WILL BE VISIBLE TO REST API
 * Transforms a database bill to a public bill.
 * Created by stew on 3/30/17.
 */
@JsonPropertyOrder("billId", "number", "congress", "resolutionType", "billType",
    "shortTitle", "billName", "officialTitle", "currentStatus",
    "currentStatusAt", "introducedAt", "updatedAt", "sponsor", "subjects")
@JsonInclude(JsonInclude.Include.NON_NULL)
class BillDelegated(private val bill: Bill,
                    private val peopleMap: Map<String, LegislatorData> = emptyMap()) : PublicBill {
    override fun getCurrentStatusLabel(): String { //todo not working??
        return getCurrentStatusDescription()
    }

    override fun getCurrentStatusDescription(): String {
        return bill.billSummary?.text ?: ""
    }

    override fun getResolutionType(): String {
        return when (bill.bill_type.isBill) {
            true -> "bill"
            false -> "resolution"
        }
    }

    //NEW minimal data
    override fun getBillName(): String {
        return "${this.getBillId()} ${this.getOfficialTitle()}"
    }

    override fun id(): Int {
        return 1
    }

    //old data
    override fun getBillId(): String {
        return "${bill.bill_type.shortLabel()} ${bill.billNumber}"
    }

    //@JsonIgnore
    //todo this is output as actionsArr :(
    override fun getActions(): Array<PublicAction>? {
        return toPublicActionCollection(bill.actionsArr).toTypedArray()
    }

    override fun getBillType(): String {
        return bill.bill_type.shortCode() //shortCode, todo need to make this a string
    }

    override fun getByRequest(): String? {
        return bill.by_request
    }

    override fun getCommitteeReports(): Array<String>? {
        return bill.committee_reports
    }

    //@JsonIgnore
    override fun getCongress(): Int {
        return bill.congressNum
    }

    //@JsonIgnore
    override fun getCosponsors(): Array<PublicLegislator>? {
        val cosponsorData: List<DelegatedLegislator> = bill.cosponsorsArr
            .map { peopleMap[it.bioguide_id] }
            .filterNotNull()
            .map { DelegatedLegislator(it) }
        return cosponsorData.toTypedArray()
    }

    //@JsonIgnore
    override fun getSponsor(): PublicLegislator? {
        val p: LegislatorData = peopleMap.get(bill.billSponsor.bioguide_id) ?: return null
        return DelegatedLegislator(p)
    }

    override fun getEnactedAs(): EnactedAs? {
        return bill.enacted_as
    }

    @JsonIgnore
    override fun getHistory(): PublicBillHistory? {
        return null//bill.billHistory
    }

    //@JsonSerialize(using = DbDateSerializer::class)
    override fun getIntroducedAt(): String { //TODO make this return type Any
        return DbDateSerializer().serializeDate(bill.introduced_at)
    }

    //@JsonIgnore
    override fun getNumber(): String? {
        return bill.billNumber
    }

    //@JsonIgnore
    override fun getCommittees(): Array<Any> {
        return bill.committeesArr
    }

    override fun getOfficialTitle(): String {
        return bill.official_title
    }

    override fun getPopularTitle(): String? {
        return bill.popular_title
    }

    //TODO
    override fun getRelatedBills(): Array<PublicRelatedBill>? {
        return null//bill.related_bills
    }

    override fun getShortTitle(): String? {
        return bill.short_title
    }

    //@JsonIgnore
    override fun getCurrentStatus(): FixedStatus { //todo make this into an enum and parse out of actions
        //val cleanStatus = bill.currentStatus.replace(':','_').toLowerCase()
        return FixedStatus.valueOfDb(bill.currentStatus)
        //return bill.currentStatus
    }

    //@JsonSerialize(using = DbDateSerializer::class)
    override fun getCurrentStatusAt(): String {
        return DbDateSerializer().serializeDate(bill.status_at)
    }

    //@JsonIgnore
    override fun getSubjects(): Array<String> {
        return bill.subjectsArr
    }

    override fun getSubjectsTopTerm(): String? {
        return bill.subjects_top_term
    }

    //@JsonIgnore
    override fun getSummary(): PublicSummary? {
        return bill.billSummary
    }

    //@JsonIgnore
    //TODO
    override fun getTitles(): Array<PublicTitle>? {
        return null//bill.titlesArr
    }

    //@JsonSerialize(using = DbDateSerializer::class)
    override fun getUpdatedAt(): String {
        return DbDateSerializer().serializeDate(bill.updated_at)
    }

    //@JsonIgnore
    override fun getUrl(): String {
        return bill.urlBill
    }
}

/**
 * Created by stew on 3/19/17.
 */
fun Bill.toPublicDelegated(): BillDelegated {
    return BillDelegated(this)
}

fun toPublicBillCollection(
    mongoQueryResult: Collection<Bill>): Collection<BillDelegated> {
    return mongoQueryResult.map(Bill::toPublicDelegated)
}

fun toPublicBillCollection(mongoQueryResult: Collection<Bill>,
                           people: Map<String, LegislatorData>): Collection<BillDelegated> {
    return mongoQueryResult.map({
        BillDelegated(it, people)
    })
}
