package bz.stew.bracken.ui.util.ui

import kotlin.js.Date
import kotlin.math.floor
import kotlin.math.round

/**
 * Created by stew on 1/28/17.
 */

class UIFormatter {
    companion object DateCompanion {
        fun prettyDate(date: Date): String {
            // Make a fuzzy time
            val delta = round(((Date().getTime()) - (date.getTime())) / 1000)

            val isFuture = delta < 0
            val minute = 60
            val hour = minute * 60
            val day = hour * 24
            val week = day * 7
            val month = week * 4
            val year = month * 12

            var fuzzy: String
            if (!isFuture) {

                fuzzy = when {
                    delta < 30 -> "just now"
                    delta < minute -> delta.toString() + " seconds ago"
                    delta < 2 * minute -> "a minute ago"
                    delta < hour -> floor(delta / minute).toString() + " minutes ago"
                    floor(delta / hour) == 1.0 -> "1 hour ago"
                    delta < day -> floor(delta / hour).toString() + " hours ago"
                    delta < day * 2 -> "yesterday"
                    delta < 2 * week -> floor(delta / day).toString() + " days ago"
                    delta < month -> floor(delta / week).toString() + " weeks ago"
                    delta < year -> floor(delta / month).toString() + " months ago"
                    else -> "over a year ago"
                }
            } else {
                fuzzy = when {
                    delta > -30 -> "very recently"
                    delta > minute -> "in " + delta.toString() + " seconds"
                    delta > 2 * minute -> "in a minute"
                    delta > hour -> "in " + floor(delta / minute).toString() + " minutes"
                    floor(delta / hour) == -1.0 -> "in 1 hour"
                    delta > day -> "in " + floor(delta / hour).toString() + " hours"
                    delta > day * 2 -> "tomorrow"
                    else -> "in a while"
                }
            }
            return fuzzy
        }
    }
}

