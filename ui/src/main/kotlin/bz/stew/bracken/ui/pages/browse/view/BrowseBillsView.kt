package bz.stew.bracken.ui.pages.browse.view

import bz.stew.bracken.ui.pages.browse.controller.BillFilters
import bz.stew.bracken.ui.extension.html.elementFixedOffset
import bz.stew.bracken.ui.extension.html.removeAllChildrenNodes
import bz.stew.bracken.ui.extension.jquery.actual
import bz.stew.bracken.ui.extension.jquery.ext.JQuery
import bz.stew.bracken.ui.extension.jquery.ext.jQuery
import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.ui.common.view.BillViewItem
import bz.stew.bracken.ui.util.animation.Animation
import bz.stew.bracken.ui.util.log.Log
import bz.stew.bracken.ui.common.view.Form
import bz.stew.bracken.ui.common.view.Identifier
import bz.stew.bracken.ui.common.view.Templates
import bz.stew.bracken.view.HtmlSelector
import bz.stew.bracken.view.View
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.browser.document

/**
 * Created by stew on 1/25/17.
 */
class BrowseBillsView(private val templater: Templates) : View() {

    //private val rootElmntSelector = rootElmtStr
    private val countBillsTextHtmlSelector = HtmlSelector(Identifier.ID, "nav-bar-billCount")
    private val openSpeed: Int = 250
    private val hideSpeed: Int = 200
    private var activeCell: HTMLElement? = null
    private val billViews = mutableMapOf<Int, BillViewItem>()
    private var visibleBills = mutableSetOf<BillViewItem>()

    fun appendModelData(bills: List<BillData>) {
        for (b: BillData in bills) {
            val existing = billViews.get(b.uniqueId)
            if (existing == null) {
                billViews.put(b.uniqueId, BillViewItem(b))
            }
        }
    }

    /**
     * Each call replaces the html values in the filter with those passed in. Repeatable.
     * TODO define these filters in another place
     */
    fun loadStatusFilter(filterEntry: BillFilters,
                         allStatus: Set<FixedStatus>) {
        val parent = getElementBySelector(filterEntry.htmlSelector())
        parent.removeAllChildrenNodes()
        parent.appendChild(Form.Option.generateHtml("All", mapOf(Pair("value", FixedStatus.NONE.ordinal.toString()))))
        //TODO sort these
        for (fs: FixedStatus in allStatus) {
            val eco = mapOf(Pair("value", fs.ordinal.toString()))
            //TODO make this display the nicer string
            parent.appendChild(Form.Option.generateHtml(fs.name, eco))
        }
    }

    fun loadMajorStatusFilter(filterEntry: BillFilters,
                              allMajorStatus: Set<MajorStatus>) {
        val parent = getElementBySelector(filterEntry.htmlSelector())
        parent.removeAllChildrenNodes()
        parent.appendChild(Form.Option.generateHtml("All", mapOf(Pair("value", MajorStatus.NONE.ordinal.toString()))))
        //TODO sort these
        for (fs: MajorStatus in allMajorStatus) {
            val eco = mapOf(Pair("value", fs.ordinal.toString()))
            parent.appendChild(Form.Option.generateHtml(fs.niceFormat(), eco))
        }
    }

    private fun updateBillCountText(billCount: Int) {
        jQuery(countBillsTextHtmlSelector.text()).get(0).innerHTML = "Showing $billCount bills"
    }

    fun showSelectedBills(bills: Collection<BillData>) {
        this.visibleBills.clear()

        bills
              .map { this.billViews[it.uniqueId] }
              .forEach { it?.let { this.visibleBills.add(it) } }

        Log.debug { "Showing #${bills.size} bills" }

        updateBillCountText(bills.size)

        val remainingBills = this.billViews.toMutableMap()
        for (bv: BillViewItem in this.visibleBills) {
            getJq(bv.selector()).addClass("billVisible").removeClass("billHidden")
            remainingBills.remove(bv.billData.uniqueId)
        }
        remainingBills.forEach {
            getJq(it.value.selector()).addClass("billHidden").removeClass("billVisible")
        }
        //TODO need to also animate the width and height.
        jQuery(".billHidden")
              .hide("slow")

        jQuery(".billVisible")
              .show("slow")
    }

    //Only call this once. generates
    fun generateAndDisplayAllBills(parent:HTMLElement, resetVisible: Boolean = true) {

        val sortedList = this.billViews.values.sorted()

        if (resetVisible) {
            this.visibleBills.clear()
        }

        Log.debug { "Showing #${sortedList.size} bills" }
        updateBillCountText(sortedList.size)

        sortedList
              .filterNot { this.visibleBills.contains(it) }
              .forEach {
                  try {
                      this.generateFromTemplate(it, parent)
                      this.initiateBill(it)
                      this.visibleBills.add(it)
                  } catch (e: Throwable) {
                      console.log(e.message)
                      console.log(e.cause)
                      console.log(e)
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
        }
        ani.setDuration(openSpeed)

        ani.setPlayBackwards(backwards)
        return ani
    }

    private fun makeHtmlCellActive(bill: HTMLElement) {
        val active = this.activeCell

        val additionalExpandHeight = 30
        if (active != null) {
            //close prior one
            val child = jQuery(active).children(".billExpanded")
            val openHeight =
                  jQuery(child).actual("height").toInt() + additionalExpandHeight
            val aniOpen: Animation = makeAnimation(openHeight,
                  "px",
                  true
            )
            aniOpen.run(openSpeed,
                  active)
            child.hide(hideSpeed)
            if (this.activeCell == bill) {
                this.activeCell = null
                return
            }
        }

        this.activeCell = bill
        val child: JQuery = jQuery(bill).children(".billExpanded")
        //TODO made this centered, and the width of the number of bills across the screen
        child.css("transform",
              "translateX(-" + expandedBillXOffset(bill) + "px)")

        val openHeight = child.actual("height").toInt() + additionalExpandHeight /// MUST be before .show
        val aniOpen: Animation = makeAnimation(openHeight, "px")
        aniOpen.run(openSpeed,
              bill)
        child.show(openSpeed)
    }

    private fun expandedBillXOffset(bill:HTMLElement):Double{
        return document.elementFixedOffset(bill).x?.minus(20) ?: 20.0
    }

    private fun makeCellActive(billSelector: HtmlSelector) {
        val jqd = jQuery(billSelector.text())
        makeHtmlCellActive(jqd.get(0))
    }

    private fun generateFromTemplate(billView: BillViewItem,
                                     parent: HTMLElement): HTMLElement {
        val billNode = templater.renderBill(billView).getHtml()
        parent.appendChild(billNode)
        return billNode
    }

    /**
     * Starts events and fades in bill
     * assumes a fixed HTML structure
     */
    private fun initiateBill(bill: BillViewItem) {
        val billSelector = bill.selector()
        val billJq = jQuery(billSelector.text())
        val view = this
        // Make active on click
        val clickHeader = billJq.children(".billGridContent")
        clickHeader.get(0).onclick = fun(e: Event) {
            e.stopImmediatePropagation()
            e.stopPropagation()
            e.preventDefault()
            view.makeCellActive(billSelector)
        }
        //finally, make it display something
        billJq.fadeIn("slow")
    }
}

