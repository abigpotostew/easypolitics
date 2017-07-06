package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.Classes
import kotlinx.html.HtmlBodyTag
import kotlinx.html.dd
import kotlinx.html.dl
import kotlinx.html.dt

/**
 * Created by stew on 3/5/17.
 */

fun HtmlBodyTag.ac(newClass:String){
	this.addClass(newClass)
}

fun HtmlBodyTag.ac(id: Classes){
	this.addClass(id.lbl)
}

fun HtmlBodyTag.ac(vararg ts: String){
	for (clazz in ts) {
		this.addClass(clazz)
	}
}

fun HtmlBodyTag.ac(vararg ts: Classes){
	for (id in ts) {
		this.addClass(id.lbl)
	}
}

fun HtmlBodyTag.renderDescriptionList(content: Map<String, (HtmlBodyTag) -> Unit>) {
	dl {
		ac(Classes.boots_dlHorizontal)
		for ((k, v) in content) {
			dt {
				ac(Classes.boots_dlHorizontalDt_small)
				+k
			}
			dd {
				ac(Classes.boots_dlHorizontalDd_small)
				v(this)
			}
		}
	}
}