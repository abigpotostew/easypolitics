package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.Classes
import kotlinx.html.HtmlBodyTag

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