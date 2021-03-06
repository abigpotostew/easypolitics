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
import bz.stew.bracken.ui.context.PageContext
import bz.stew.bracken.ui.extension.niceClamp
import bz.stew.bracken.ui.id.Base64
import bz.stew.bracken.ui.pages.browse.view.BootstrapTemplates
import bz.stew.bracken.ui.pages.browse.view.BrowseBillsView
import bz.stew.bracken.ui.util.log.Log
import bz.stew.bracken.view.HtmlSelector
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stewart.bracken.shared.data.party.Party
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

/**
 * Created by stew on 1/25/17.
 */
class BrowseBillsController(rootElmt: HtmlSelector,
                            private val requestUrl: BillRestQueryUrl,
                            model: Model<BillData>,
                            pageContext: PageContext)
    : PageController<BrowseBillsView, BillData>(BrowseBillsView(BootstrapTemplates()), model, pageContext) {

    private val rootSelector = rootElmt
    private val requestService = BillRestService()
    private var lastSuccessfulQuery: BillDataServiceEndpoint? = null
    /**
     * This serves as the async lock to prevent multiple query being executed by user and bamboozling the controller
     */
    private var inProgressQuery: BillDataServiceEndpoint? = null


    /**
     * Start listening to the forms
     */
    private fun startListeningFilterForms() {
        this.view.getElementBySelector(BillFilters.PARTY.htmlSelector()).addEventListener("change", this::partyFilter)
        this.view.getElementBySelector(BillFilters.FIXEDSTATUS.htmlSelector()).addEventListener("change",
            this::billStatusFilter)
        this.view.getElementBySelector(BillFilters.DATEINTROSTART.htmlSelector()).addEventListener("change", {
            introducedDateFilter(it, IndexOperation.GreaterThanOrEqual)
        })
        this.view.getElementBySelector(BillFilters.DATEINTROEND.htmlSelector()).addEventListener("change", {
            introducedDateFilter(it, IndexOperation.LessThanOrEqual)
        })
        this.view.getElementBySelector(BillFilters.LASTMAJORSTATUS.htmlSelector()).addEventListener("change",
            this::billMajorStatusFilter)

        val loadMoreBtn = this.view.getElementBySelector(HtmlSelector(identifier = Identifier.ID,
            selectorText = "loadNextPageBtn"))
        loadMoreBtn.addEventListener("click", {
            nextPageQuery()
        })
    }

    @Suppress("unused")
    fun stopListening() {
        //TODO use this
        this.view.getElementBySelector(BillFilters.PARTY.htmlSelector()).removeEventListener("change",
            this::partyFilter)
        this.view.getElementBySelector(BillFilters.FIXEDSTATUS.htmlSelector()).removeEventListener("change",
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
        this.view.generateAndDisplayAllBills(getRootElement())
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
        if (this.inProgressQuery != null) {
            return
        } else {
            this.inProgressQuery = nextQuery
        }
        this.requestService.sendBillRequest(nextQuery, {
            val view = controller.view
            val data = it.response
            if (data != null) {
                controller.model.loadBillData(data, true)
                view.appendModelData(this.model.getBillData())
                view.loadStatusFilter(STATUS_INDEX.filterType(), STATUS_INDEX.allKeys())
                view.loadMajorStatusFilter(MAJOR_STATUS_INDEX.filterType(), MAJOR_STATUS_INDEX.allKeys())
                view.generateAndDisplayAllBills(getRootElement(), false)
                this.lastSuccessfulQuery = nextQuery
                this.inProgressQuery = null
            }
        })
    }

    /**
     * Main point to load new data from remote endpoint
     */
    override fun init(callback: () -> Unit) {
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

    fun getRootElement(): HTMLElement {
        return this.view.getElementBySelector(this.rootSelector)
    }
}