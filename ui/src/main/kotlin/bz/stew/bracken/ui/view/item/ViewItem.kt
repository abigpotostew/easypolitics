package bz.stew.bracken.view.item

import bz.stew.bracken.view.HtmlSelector

/**
 * Created by stew on 1/31/17.
 */
interface ViewItem : Comparable<ViewItem> {

   fun sortBy(): Int

   fun selector(): HtmlSelector

   override fun compareTo(other: ViewItem): Int
}