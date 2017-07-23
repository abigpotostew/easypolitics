package bz.stew.bracken.ui.util.ui

import kotlin.js.Date
import kotlin.js.Math

/**
 * Created by stew on 1/28/17.
 */

public class UIFormatter {
    companion object DateCompanion {
        public fun prettyDate(date: Date): String {
            // Make a fuzzy time
            val delta = Math.round(((Date().getTime()) - (date.getTime() as Double)) / 1000)

            val isFuture = delta < 0
            val minute = 60
            val hour = minute * 60
            val day = hour * 24
            val week = day * 7
            val month = week * 4

            var fuzzy: String
            if (!isFuture) {

                fuzzy = when {
                    delta < 30 -> "just now"
                    delta < minute -> delta.toString() + " seconds ago"
                    delta < 2 * minute -> "a minute ago"
                    delta < hour -> Math.floor(delta / minute).toString() + " minutes ago"
                    Math.floor(delta / hour) == 1 -> "1 hour ago"
                    delta < day -> Math.floor(delta / hour).toString() + " hours ago"
                    delta < day * 2 -> "yesterday"
                    delta < 2 * week -> Math.floor(delta / day).toString() + " days ago"
                    delta < month -> Math.floor(delta / week).toString() + " weeks ago"
                    //delta < month               -> Math.floor(delta / week).toString() +" weeks ago"
                    else -> "over a month ago"
                }
            } else {
                fuzzy = when {
                    delta > -30 -> "very recently"
                    delta > minute -> "in " + delta.toString() + " seconds"
                    delta > 2 * minute -> "in a minute"
                    delta > hour -> "in " + Math.floor(delta / minute).toString() + " minutes"
                    Math.floor(delta / hour) == -1 -> "in 1 hour"
                    delta > day -> "in " + Math.floor(delta / hour).toString() + " hours"
                    delta > day * 2 -> "tomorrow"
                    else -> "in a while"
                }
            }
            return fuzzy
        }
    }
}

