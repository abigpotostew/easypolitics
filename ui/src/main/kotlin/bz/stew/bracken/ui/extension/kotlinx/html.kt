package bz.stew.bracken.ui.extension.kotlinx

import bz.stew.bracken.ui.view.html.Classes
import kotlinx.html.HtmlBodyTag
import kotlinx.html.dd
import kotlinx.html.dl
import kotlinx.html.dt

fun HtmlBodyTag.ac(newClass: String) {
   this.addClass(newClass)
}

fun HtmlBodyTag.ac(id: Classes) {
   this.addClass(id.lbl)
}

fun HtmlBodyTag.ac(vararg ts: String) {
   for (clazz in ts) {
      this.addClass(clazz)
   }
}

fun HtmlBodyTag.ac(vararg ts: Classes) {
   for (id in ts) {
      this.addClass(id.lbl)
   }
}

fun HtmlBodyTag.horzizontalDescriptionList(content: Map<String, (HtmlBodyTag) -> Unit>) {
   dl(Classes.boots_row) {
      for ((k, v) in content) {
         dt {
            ac(Classes.boots_colSm2, Classes.boots_colXl1)
            +k
         }
         dd {
            ac(Classes.boots_colSm10, Classes.boots_colXl11)
            v(this)
         }
      }
   }
}