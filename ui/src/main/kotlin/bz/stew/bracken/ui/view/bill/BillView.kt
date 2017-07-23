package bz.stew.bracken.ui.view.bill

//import bz.stew.bracken.extension.jquery.get
import bz.stew.bracken.ui.controller.bill.filter.BillFilters
import bz.stew.bracken.ui.extension.html.eachChildClass
import bz.stew.bracken.ui.extension.html.eachChildId
import bz.stew.bracken.ui.extension.html.elementFixedOffset
import bz.stew.bracken.ui.extension.html.removeAllChildrenNodes
import bz.stew.bracken.ui.extension.jquery.*
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stew.bracken.ui.model.types.bill.status.BillStatus
import bz.stew.bracken.ui.util.animation.Animation
import bz.stew.bracken.ui.util.log.Log
import bz.stew.bracken.ui.util.ui.UIFormatter
import bz.stew.bracken.ui.view.html.Form
import bz.stew.bracken.ui.view.html.Identifier
import bz.stew.bracken.ui.view.html.Templates
import bz.stew.bracken.ui.view.item.BillViewItem
import bz.stew.bracken.view.HtmlSelector
import bz.stew.bracken.view.View
import bz.stew.bracken.view.item.ViewItem
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import jquery.JQuery
import jquery.jq
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.addClass

/**
 * Created by stew on 1/25/17.
 */
class BillView(rootElmtStr: HtmlSelector, val templater: Templates) : View(rootElmtStr) {

   val GENERATE_FROM_TEMPLATE = true
   val COUNT_TEXT_HTML_SELECTOR = HtmlSelector(Identifier.ID, "nav-bar-billCount")
   val OPEN_SPEED: Int = 250
   val HIDE_SPEED: Int = 200
   val OPEN_HEIGHT: Int = 30
   val OPEN_HEIGHT_UNITS: String = "rem"
   var activeCell: HTMLElement? = null
   var internalBillId: Int = 0
   //private val billViews = ArrayList<BillViewItem>()
   private val billViews = mutableMapOf<Int, BillViewItem>()
   val SKIP_PROFILE_IMG: Boolean = true

   var visibleBills = mutableSetOf<BillViewItem>()

   //var generatedFilterKeys:Map<BillFilters,Collection<BillViewItem>> = mapOf<BillFilters,Collection<BillViewItem>>()

   fun appendModelData(bills: List<BillData>) {
      for (b: BillData in bills) {
         val existing = billViews.get(b.uniqueId)
         if (existing == null) {
            //billViews.add(BillViewItem(b))
            billViews.put(b.uniqueId, BillViewItem(b))
         }
      }
   }

   /**
    * Each call replaces the html values in the filter with those passed in. Repeatable.
    * TODO define these filters in another place
    */
   //
   fun loadStatusFilter(filterEntry: BillFilters,
                        allStatus: Set<FixedStatus>) {
      val parent = getElement(filterEntry.htmlSelector())
      parent.removeAllChildrenNodes()
      //TODO sort these
      for (fs: FixedStatus in allStatus) {
         val eco = mapOf(Pair("value", fs.ordinal.toString()))
         //TODO make this display the nicer string
         parent.appendChild(Form.Option.generateHtml(fs.name, eco))
      }
   }

   fun loadMajorStatusFilter(filterEntry: BillFilters,
                             allMajorStatus: Set<MajorStatus>) {
      val parent = getElement(filterEntry.htmlSelector())
      parent.removeAllChildrenNodes()
      //TODO sort these
      for (fs: MajorStatus in allMajorStatus) {
         val eco = mapOf(Pair("value", fs.ordinal.toString()))
         parent.appendChild(Form.Option.generateHtml(fs.niceFormat(), eco))
      }
   }

   fun updateBillCountText(billCount: Int) {
      jq(COUNT_TEXT_HTML_SELECTOR.text()).get(0).innerHTML = "Showing ${billCount} bills"
   }

   fun showSelectedBills(bills: Collection<BillData>) {
      this.visibleBills.clear()
      for (b: BillData in bills) {
         val bv = this.billViews[b.uniqueId]
         bv?.let { this.visibleBills.add(bv) }
      }

      Log.debug { "Showing #${bills.size} bills" }

      updateBillCountText(bills.size)

      val remainingBills = this.billViews.toMutableMap()//mutableMapOf<Int,BillViewItem>()
      //allBills.putAll()
      for (bv: BillViewItem in this.visibleBills) {
         getJq(bv.selector()).addClass("billVisible").removeClass("billHidden")
         //.fadeIn("slow")
         remainingBills.remove(bv.billData.uniqueId)
      }
      remainingBills.forEach {
         getJq(it.value.selector()).addClass("billHidden").removeClass("billVisible")
      }
      //TODO need to also animate the width and height.
      //js("$('.billHidden').velocity('transition.bounceLeftOut',{duration: 500, stagger:10})") //.velocity({ width: 0},500)
      //js("$('.billVisible').velocity('transition.bounceRightIn',{duration: 500, stagger:10})")
      jq(".billHidden")
            //.velocity("fadeOut",js("({duration: 500})"))
            .hide("slow")

      jq(".billVisible")
            //.velocity("fadeIn",js("({duration: 500})")) //DOESN'T WORK
            .show("slow")
   }

   //Only call this once. generates
   fun generateAndDisplayAllBills(resetVisible: Boolean = true) {
      var billListJQ: JQuery = getJq(rootElementSelector)

      val sortedList = this.billViews.values.sorted()

      if (resetVisible) {
         this.visibleBills.clear()
      }

      Log.debug { "Showing #${sortedList.size} bills" }
      updateBillCountText(sortedList.size)

      for (i: ViewItem in sortedList) {
         if (i is BillViewItem && !this.visibleBills.contains(i)) {
            //add bill to html
            try {
               if (GENERATE_FROM_TEMPLATE) {
                  this.generateFromTemplate(i, billListJQ)
               } else {
                  this.generateBillView(i, billListJQ)
               }
               this.initiateBill(i)
               this.visibleBills.add(i)

            } catch (e: Throwable) {
               println(e.toString())
            }
         }
      }
   }

   private fun makeAnimation(position: Int,
                             units: String,
                             backwards: Boolean = false): Animation {
      val ani: Animation = object : Animation() {
         override fun onUpdate(progress: Double) {
            element!!.style.marginBottom = (progress * position).toString() + units
         }

         override fun onComplete() {
            super.onComplete()
            //clearOnStartCallbacks()
            //clearOnEndCallbacks()
         }
      }
      ani.setDuration(OPEN_SPEED)

      ani.setPlayBackwards(backwards)
      return ani
   }

   fun makeHtmlCellActive(bill: HTMLElement) {
      val active = this.activeCell

      if (active != null) {
         //close prior one
         val child = jq(active).children(".billExpanded")
         val openHeight = jq(child).height().toInt()
         val aniOpen: Animation = makeAnimation(openHeight,
               "px",
               true
         )
         aniOpen.run(OPEN_SPEED,
               active)
         child.hide(HIDE_SPEED)
         if (this.activeCell == bill) {
            this.activeCell = null
            return
         }
      }

      this.activeCell = bill
      val child: JQuery = jq(bill).children(".billExpanded")
      //TODO made this centered, and the width of the number of bills across the screen
      child.css("transform",
            "translateX(-" + document.elementFixedOffset(bill).x + "px)")

      val openHeight = child.actual("height").toInt() /// MUST be before .show
      val aniOpen: Animation = makeAnimation(openHeight, "px")
      aniOpen.run(OPEN_SPEED,
            bill)
      child.show(OPEN_SPEED)
   }

   fun makeCellActive(billSelector: HtmlSelector) {
      val jqd: dynamic = jq(billSelector.text())
      makeHtmlCellActive(jqd[0] as HTMLElement)
   }

   fun generateFromTemplate(billView: BillViewItem,
                            parentJq: JQuery): HTMLElement {
      val billString = templater.renderBill(billView).getHtml()
      val billNode = jq(billString)
      parentJq.append(billNode)
      return billNode.get(0)
   }

   /*
   generates HTML, this should eventually be switched out with a backend templating
    */
   fun generateBillView(billView: BillViewItem,
                        parentJq: JQuery): HTMLElement {
      val bd = billView.billData
      this.internalBillId++
      val billId = billView.selector()
      val name = bd.officialTitle
      val statusDescription = bd.status.description()
      val sponsorName = bd.sponsor.getOfficialName()
      val introDate = UIFormatter.prettyDate(bd.intro_date)
      val status: BillStatus = bd.status
      val statusLabel: String = status.label()
      val statusDescr: String = status.description()
      val link: String = bd.link
      val congress: Int = bd.congress
      val partyNameClass = bd.sponsor.getParty().lowercaseName() //TODO this is kinda janky using as a direct css identifier :(
      val billResolType = bd.bill_resolution_type
      val billType = bd.bill_type
      val billSponsorProfileImg = billView.sponsorImageUrl()

      val view = this

      val billJq: JQuery = htmlGen.buildElement("bill")

      //do all properly typed stuff
      val billHtml: HTMLElement = billJq.get(0)
      //billHtml.addClass("")
      billHtml.addClass(partyNameClass)

      parentJq.append(billJq)
      billJq.hide()

      //make sure the element gets the id or class that it should be identified with
      billId.addToJqElement(billJq)

      //could clean up thiss interface
      billHtml.eachChildClass("billTitle",
            { this.innerHTML = billView.shortLabel() })
      billHtml.eachChildClass("billSponsor",
            { this.innerHTML = sponsorName })
      billHtml.eachChildClass("billDescription",
            { this.innerHTML = name })
      billHtml.eachChildClass("billStatus",
            { this.innerHTML = "Status: " + statusLabel })
      billHtml.eachChildClass("billDate", { this.innerHTML = introDate })
      billHtml.eachChildClass("billStatusDescription",
            { this.innerHTML = statusDescr })

      //billJq.children(".billExpanded").get(0).eachChildClass("billtitle",{it?.innerHTML = billView.shortLabel()})

      //todo tracker
      val lastMajorStatusIdx = when (status.lastMajorStatus()) {
         MajorStatus.INTRODUCED -> "intro"
         MajorStatus.PASSED_HOUSE -> "house"
         MajorStatus.PASSED_SENATE -> "senate"
         MajorStatus.SIGNED_PRESIDENT -> "president"
         MajorStatus.LAW -> "law"
         else -> ""
      }

      billHtml.eachChildClass("billTracker-$lastMajorStatusIdx", {
         this.setAttribute("style", "background-color:yellow;")
      })

      //todo link
      billHtml.eachChildClass("billLink", {
         this.setAttribute("href", link)
         this.innerHTML = "Link to govtrack"
//            val linkElmt = document.createElement("a", {
//
//                this.innerHTML = "Link to govtrack"
//            })
//            it?.appendChild(linkElmt)
      })

      // Make tab href and id unique to this bill... required for tabs to work
      (0..3)
            .map { "bill-exp-nav-tab" + it }
            .forEach { txt ->
               val newSelector = HtmlSelector(Identifier.ID, txt + "-" + billId.suffix())
               billHtml.eachChildClass(txt, {

                  this.setAttribute("href", newSelector.text())
               })
               billHtml.eachChildId(txt, {
                  //it.setAttribute("href", txt+"-" + billId.suffix())
                  it.id = newSelector.suffix()
               })
            }

      if (!SKIP_PROFILE_IMG) {
         billHtml.eachChildClass("billExpandedSponsorImg", {
            this.setAttribute("src", billSponsorProfileImg)
         })
      }

      return billHtml
   }

   /**
    * Starts events and fades in bill
    * assumes a fixed HTML structure
    */
   fun initiateBill(bill: BillViewItem) {
      val billSelector = bill.selector()
      val billJq = jq(billSelector.text())
      val billElmt: HTMLElement = billJq.get(0)
      val view = this
      // Make active on click
      val clickHeader = jq(billJq).children(".billGridContent")
      clickHeader.get(0).onclick = fun(e: Event) {
         e.stopImmediatePropagation()
         e.stopPropagation()
         e.preventDefault()
         view.makeCellActive(billSelector)
      }
      //by default it's hidden, nope
      //billJq.hide()
      //finally, make it display something
      billJq.fadeIn("slow")
   }

}

