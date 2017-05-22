package bz.stew.bracken.ui.controller.bill

import bz.stew.bracken.ui.controller.Controller
import bz.stew.bracken.ui.controller.bill.filter.BillFilters
import bz.stew.bracken.ui.extension.niceClamp
import bz.stew.bracken.ui.model.BillModelGovTrack
import bz.stew.bracken.ui.model.Model
import bz.stew.bracken.ui.model.index.*
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stew.bracken.ui.service.RequestCallback
import bz.stew.bracken.ui.service.ServerRequestDispatcher
import bz.stew.bracken.ui.util.JsonUtil
import bz.stew.bracken.ui.view.bill.BillView
import bz.stew.bracken.ui.view.html.bootstrap.BootstrapTemplates
import bz.stew.bracken.view.HtmlSelector
import bz.stewart.bracken.shared.data.party.Party
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

/**
 * Created by stew on 1/25/17.
 */

class BillController(rootElmt: HtmlSelector, model: Model = BillModelGovTrack()) : Controller(
      BillView(rootElmt, BootstrapTemplates()),
      model) {


   /*override fun loadData(dataRequest: DataRequest, onComplete: (Controller) -> Unit) {
       val controller = this
       downloadBillsLoadData(dataRequest.request(), {
           controller.generateAndDisplayAllBills()
           controller.view.setLoading(false);
           println("done loading")
       })
   }*/

   override fun onParseError() {
      throw RuntimeException("") //To change body of created functions use File | Settings | File Templates.
   }

   fun applyFilter(filter: BillFilters,
                   selectVal: Any?) {
      val filtered: List<BillData> = (this.model).getBillData().filter {
         filter.predicate(it, selectVal)
      }
      (this.view as BillView).showSelectedBills(filtered)

   }

   /**
    * Start listening to the forms
    */
   fun startListeningFilterForms() {
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
   }

   fun stopListening() {
      this.view.getElement(BillFilters.PARTY.htmlSelector()).removeEventListener("change", this::partyFilter)
      this.view.getElement(BillFilters.FIXEDSTATUS.htmlSelector()).removeEventListener("change",
                                                                                       this::billStatusFilter)
   }

   fun introducedDateFilter(event: Event,
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
         (this.view as BillView).showSelectedBills(
               INTRO_DATE_INDEX.instancesByOperator(operator, validDate))
      }
   }

   fun partyFilter(event: Event) {
      val target: EventTarget? = event.target
      if (target != null && target is HTMLSelectElement) {
         val party: Party = try {
            Party.valueOf(target.value)
         } catch (e: IllegalArgumentException) {
            Party.NONE
         }
         //this.applyFilter(BillFilters.PARTY, party)
         (this.view as BillView).showSelectedBills(PARTY_INDEX.instancesWith(party))
      }
   }

   fun billMajorStatusFilter(event: Event) {
      //TODO make this filtering more generic
      val target: EventTarget? = event.target
      if (target != null && target is HTMLSelectElement) {
         val majorStatus: MajorStatus = try {
            MajorStatus.valueAt(target.value.toInt())

         } catch(e: IllegalArgumentException) {
            throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
         } catch(e: NumberFormatException) {
            throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
         }
         (this.view as BillView).showSelectedBills(MAJOR_STATUS_INDEX.instancesWith(majorStatus))
      }
   }

   fun billStatusFilter(event: Event) {
      //TODO make this filtering more generic
      val target: EventTarget? = event.target
      if (target != null && target is HTMLSelectElement) {
         val fixedStatus: FixedStatus = try {
            FixedStatus.valueAt(target.value.toInt())

         } catch(e: IllegalArgumentException) {
            throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
         } catch(e: NumberFormatException) {
            throw RuntimeException("you fuxed up the status filter for select value of ${target.value}")
         }
         (this.view as BillView).showSelectedBills(STATUS_INDEX.instancesWith(fixedStatus))
      }
   }

   fun showBills() {


      if (this.view is BillView) {
         this.view.initiateModelData((this.model).getBillData())
         this.model.indexData()
         //todo make this filtering more generic
         this.view.loadStatusFilter(STATUS_INDEX.filterType(), STATUS_INDEX.allKeys())
         this.view.loadMajorStatusFilter(MAJOR_STATUS_INDEX.filterType(), MAJOR_STATUS_INDEX.allKeys())
         this.view.generateAndDisplayAllBills()
         startListeningFilterForms()
      }
   }

   fun downloadBillsLoadData(requestUrl: String,
                             onDownload: (Controller) -> Unit) {

      val controller: Controller = this
      ServerRequestDispatcher().sendRequest(
            requestUrl,
            object : RequestCallback() {
               override fun onLoad(response: String) {
                  var parse: dynamic
                  try {
                     parse = JsonUtil.parse(response)
                     controller.model.loadBillData(parse)
                  } catch (e: Throwable) {
                     error("Error parsing json response from data source: \n\t" + e.toString())

                  }
                  onDownload(controller)
               }
            }
                                           )
   }
}

//private fun  String.parseInt(): Double {}

//public inline fun jsDate(): Date = js("new Date(year,month,day)")