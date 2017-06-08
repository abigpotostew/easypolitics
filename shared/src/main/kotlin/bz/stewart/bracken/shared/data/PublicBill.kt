package bz.stewart.bracken.shared.data

import bz.stewart.bracken.shared.data.person.PublicLegislator

/**
 * Created by stew on 3/19/17.
 */
interface PublicBill {
   @BillRequired fun getBillId(): String //"H.R. 736"
   @BillRequired fun getBillName(): String //"H.R. 736: _description_"
   fun getActions(): Array<PublicAction>?
   @BillRequired fun getBillType(): String
   @BillRequired fun getByRequest(): String?
   fun getCommitteeReports(): Array<String>?  //ignore this is not documented
   @BillRequired fun getCongress(): Int
   fun getCosponsors(): Array<PublicLegislator>?
   fun getEnactedAs(): EnactedAs?
   fun getHistory(): PublicBillHistory?
   @BillRequired fun getIntroducedAt(): String? //rly a date
   @BillRequired fun getNumber(): String? //todo make this an Int
   fun getCommittees(): Array<Any>?
   fun getOfficialTitle(): String?
   fun getPopularTitle(): String?
   fun getRelatedBills(): Array<PublicRelatedBill>?
   fun getShortTitle(): String?
   @BillRequired fun getSponsor(): PublicLegislator?
   @BillRequired fun getCurrentStatus(): FixedStatus
   @BillRequired fun getCurrentStatusAt(): String //rly a date
   @BillRequired fun getCurrentStatusDescription(): String
   @BillRequired fun getCurrentStatusLabel(): String
   fun getSubjects(): Array<String>?
   fun getSubjectsTopTerm(): String?  //top descriptor tag for this bill
   fun getSummary(): PublicSummary?
   fun getTitles(): Array<PublicTitle>?
   fun getUpdatedAt(): String? //rly a date
   fun getUrl(): String?
   @BillRequired fun id(): Int
   @BillRequired fun getResolutionType(): String //bill or resolution

}
