package bz.stew.bracken.ui.util.date

import bz.stew.bracken.ui.extension.html.DateJs
import bz.stew.bracken.ui.extension.html.asJsDate
import kotlin.js.Date
import kotlin.math.floor
import kotlin.math.round

/**
 * Created by stew on 1/28/17.
 */

class DateFormatter private constructor() {
    companion object DateCompanion {
        fun fuzzyDate(date: Date): String {
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

        fun prettyDate(date: Date): String {
            return prettyDate(asJsDate(date))
        }

        fun prettyDate(date: DateJs): String {
            val amPm = getAmPmString(date)
            val MM = date.getMonth() + 1
            val dd = date.getDay()
            val yy = date.getFullYear()
            val hh = date.getHours()
            val hh12 = if (hh == 0) 12 else if (hh > 12) hh - 12 else hh
            val mm = leftPad(date.getMinutes(), 2)
            return "$MM/$dd/$yy, $hh12:$mm $amPm"
        }

        fun leftPad(n: Int, width: Int, pad: String = "0"): String {
            return leftPad(n.toString(), width, pad)
        }

        fun leftPad(str: String, width: Int, pad: String = "0"): String {
            var n = str
            while (n.length < width) {
                n = pad + n
            }
            return n
        }

        fun getAmPmString(date: DateJs): String = if (date.getHours() > 11) "PM" else "AM"
    }
}