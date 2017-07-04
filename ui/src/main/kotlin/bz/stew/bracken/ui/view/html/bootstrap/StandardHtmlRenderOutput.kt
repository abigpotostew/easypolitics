package bz.stew.bracken.ui.view.html.bootstrap

import bz.stew.bracken.ui.view.html.HtmlRenderOutput

/**
 * Created by stew on 7/4/17.
 */
class StandardHtmlRenderOutput(val htmlString: String) : HtmlRenderOutput {
   override fun getHtml(): String {
      return htmlString
   }

   override fun toString(): String {
      return htmlString
   }
}