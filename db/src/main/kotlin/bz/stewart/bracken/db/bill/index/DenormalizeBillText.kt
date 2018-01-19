package bz.stewart.bracken.db.bill.index

import bz.stewart.bracken.db.bill.data.Bill

/**
 * For index, gather all text values and combine into an array for the text index.
 */
class DenormalizeBillText(val bill: Bill) {

    fun setDenormalizedText() {
        val text = mutableListOf<String>()

        text.add(bill.bill_id)

        bill.subjects_top_term?.let { text.add(it) }

        bill.subjectsArr.forEach { text.add(it) }

        text.add(bill.official_title)

        bill.titlesArr.forEach { text.add(it.title) }

        val array = text.toTypedArray()
        bill.denormalizedAllText = array
    }
}