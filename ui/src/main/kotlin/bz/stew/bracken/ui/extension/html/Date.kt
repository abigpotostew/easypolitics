package bz.stew.bracken.ui.extension.html

import kotlin.js.Date

/**
 * Created by stew on 2/9/17.
 */
//@JsName("Date")
//public external fun jsDate(year:Int,month:Int,day:Int): Date = definedExternally

//public external class Date(year:Int,month:Int,day:Int){
//}

inline fun jsDate(year: Int, month: Int, day: Int) : Date = js("new Date(year,month,day)")
inline fun jsDate(year: Int, month: Int, day: Int, hour: Int) : Date = js("new Date(year,month,day,hour)")
inline fun jsDate(): Date = js("new Date()")

fun emptyDate() = jsDate(1970, 0, 1)//jsDate(1970,0,1)

fun jsParseDate(date: String): Date = js("new Date(Date.parse(date))")