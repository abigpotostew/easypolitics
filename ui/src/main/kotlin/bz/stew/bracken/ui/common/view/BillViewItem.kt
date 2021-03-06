package bz.stew.bracken.ui.common.view

import bz.stew.bracken.ui.common.bill.BillData
import bz.stew.bracken.view.HtmlSelector
import bz.stew.bracken.view.item.ViewItem

/**
 * wraps a model item and contains logic for viewing related things based on data model such as sorting
 * Created by stew on 1/31/17.
 */
class BillViewItem(val billData: BillData) : ViewItem {

    fun trueTitle(): String {
        if (billData.officialTitle.isBlank()) {
            return billData.shortTitle
        }
        return billData.officialTitle
    }

    fun shortLabel(): String {
        return this.billData.bill_type.shortLabel() + " " + this.billData.number
    }

    override fun sortBy(): Int {
        return this.billData.number
    }

    override fun selector(): HtmlSelector {
        return HtmlSelector(Identifier.ID, "bill" + (this.billData.billId).hashCode())
    }

    override fun compareTo(other: ViewItem): Int {
        return this.sortBy() - other.sortBy()
    }

    fun sponsorImageUrl(): String {
        val twitterId = this.billData.sponsor.getTwitter()
        return "https://twitter.com/$twitterId/profile_image?size=original"
    }
}