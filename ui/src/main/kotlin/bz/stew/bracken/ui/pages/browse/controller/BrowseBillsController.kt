package bz.stew.bracken.ui.pages.browse.controller

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.controller.PageController
import bz.stew.bracken.ui.common.index.INTRO_DATE_INDEX
import bz.stew.bracken.ui.common.index.IndexOperation
import bz.stew.bracken.ui.common.index.MAJOR_STATUS_INDEX
import bz.stew.bracken.ui.common.index.PARTY_INDEX
import bz.stew.bracken.ui.common.index.STATUS_INDEX
import bz.stew.bracken.ui.common.model.Model
import bz.stew.bracken.ui.common.query.BillDataServiceEndpoint
import bz.stew.bracken.ui.common.query.BillRestQueryUrl
import bz.stew.bracken.ui.common.service.BillRestService
import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.ui.extension.niceClamp
import bz.stew.bracken.ui.pages.browse.view.BootstrapTemplates
import bz.stew.bracken.ui.pages.browse.view.BrowseBillsView
import bz.stew.bracken.ui.util.log.Log
import bz.stew.bracken.view.HtmlSelector
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stewart.bracken.shared.data.party.Party
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

/**
 * Created by stew on 1/25/17.
 */
class BrowseBillsController(rootElmt: HtmlSelector,
                            private val requestUrl: BillRestQueryUrl,
                            model: Model<BillData>) : PageController<BrowseBillsView, BillData>(
        BrowseBillsView(rootElmt, BootstrapTemplates()),
        model) {

    private val requestService = BillRestService()
    private var lastSuccessfulQuery: BillDataServiceEndpoint? = null
    private var inProgressQuery: BillDataServiceEndpoint? = null


    /**
     * Start listening to the forms
     */
    private fun startListeningFilterForms() {
        this.view.getElement(BillFilters.PARTY.htmlSelector()).addEventListener("change", this::partyFilter)
        this.view.getElement(BillFilters.FIXEDSTATUS.htmlSelector()).addEventListener("change", this::billStatusFilter)
        this.view.getElement(BillFilters.DATEINTROSTART.htmlSelector()).addEventListener("change", {
            introducedDateFilter(it, IndexOperation.GreaterThanOrEqual)
        })
        this.view.getElement(BillFilters.DATEINTROEND.htmlSelector()).addEventListener("change", {
            introducedDateFilter(it, IndexOperation.LessThanOrEqual)
        })
        this.view.getElement(BillFilters.LASTMAJORSTATUS.htmlSelector()).addEventListener("change",
                this::billMajorStatusFilter)

        this.view.getElement(HtmlSelector(identifier = Identifier.ID,
                selectorText = "loadNextPageBtn")).addEventListener("click", {
            nextPageQuery()
        })
    }

    @Suppress("unused")
    fun stopListening() {
        //TODO use this
        this.view.getElement(BillFilters.PARTY.htmlSelector()).removeEventListener("change", this::partyFilter)
        this.view.getElement(BillFilters.FIXEDSTATUS.htmlSelector()).removeEventListener("change",
                this::billStatusFilter)
    }

    private fun introducedDateFilter(event: Event,
                                     operator: IndexOperation) {
        val target: EventTarget? = event.target
        if (target != null && target is HTMLInputElement) {
            val inputDate: Double = try {
                target.valueAsNumber
            } catch (e: IllegalArgumentException) {
                0.0
            }
            val validDate = niceClamp(inputDate, INTRO_DATE_INDEX.minKey(), INTRO_DATE_INDEX.maxKey(),
                    operator == IndexOperation.GreaterThanOrEqual)
            println("VALUE IS: $validDate")
            this.view.showSelectedBills(
                    INTRO_DATE_INDEX.instancesByOperator(operator, validDate))
        }
    }

    private fun partyFilter(event: Event) {
        val target: EventTarget? = event.target
        if (target != null && target is HTMLSelectElement) {
            val party: Party = try {
                Party.valueOf(target.value)
            } catch (e: IllegalArgumentException) {
                Party.NONE
            }
            this.view.showSelectedBills(PARTY_INDEX.instancesWith(party))
        }
    }

    private fun billMajorStatusFilter(event: Event) {
        //TODO make this filtering more generic
        val target: EventTarget? = event.target
        if (target != null && target is HTMLSelectElement) {
            val majorStatus: MajorStatus = try {
                MajorStatus.valueAt(target.value.toInt())

            } catch (e: IllegalArgumentException) {
                throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
            } catch (e: NumberFormatException) {
                throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
            }
            this.view.showSelectedBills(MAJOR_STATUS_INDEX.instancesWith(majorStatus))
        }
    }

    private fun billStatusFilter(event: Event) {
        //TODO make this filtering more generic
        val target: EventTarget? = event.target
        if (target != null && target is HTMLSelectElement) {
            val fixedStatus: FixedStatus = try {
                FixedStatus.valueAt(target.value.toInt())

            } catch (e: IllegalArgumentException) {
                throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
            } catch (e: NumberFormatException) {
                throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
            }
            this.view.showSelectedBills(STATUS_INDEX.instancesWith(fixedStatus))
        }
    }

    /**
     * First time only setup.
     */
    fun startupSetupUi() {
        this.view.appendModelData((this.model).getBillData())

        //todo make this filtering more generic
        this.view.loadStatusFilter(STATUS_INDEX.filterType(), STATUS_INDEX.allKeys())
        this.view.loadMajorStatusFilter(MAJOR_STATUS_INDEX.filterType(), MAJOR_STATUS_INDEX.allKeys())
        this.view.generateAndDisplayAllBills()
        startListeningFilterForms()

    }

    private fun nextPageQuery() {
        val lastQuery = this.lastSuccessfulQuery
        if (this.inProgressQuery != null || lastQuery == null) {
            Log.warning("Can't query to next page since no prior successful query or a query is in progress.")
            return
        }
        val nextQuery = (lastQuery as BillRestQueryUrl).nextPage()
        val controller = this
        this.requestService.sendBillRequest(nextQuery, {
            val view = controller.view
            val response = it.rawResponse
            var data = it.response
            if (data != null) {
                controller.model.loadBillData(data, true)
                view.appendModelData(this.model.getBillData())
                view.loadStatusFilter(STATUS_INDEX.filterType(), STATUS_INDEX.allKeys())
                view.loadMajorStatusFilter(MAJOR_STATUS_INDEX.filterType(), MAJOR_STATUS_INDEX.allKeys())
                view.generateAndDisplayAllBills(false)
            }
        })
    }

    /**
     * Main point to load new data from remote endpoint
     */
    override fun init(callback:() -> Unit) {
        val controller = this
        this.inProgressQuery = this.requestUrl
        this.requestService.sendBillRequest(this.requestUrl, {
            this.lastSuccessfulQuery = this.inProgressQuery
            this.inProgressQuery = null
            val bills = it.response
            if (bills != null) {
                controller.model.loadBillData(bills, false)
            }
            callback.invoke()
        })
    }
}