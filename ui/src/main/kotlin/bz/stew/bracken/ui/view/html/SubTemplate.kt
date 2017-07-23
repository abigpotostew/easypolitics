package bz.stew.bracken.ui.view.html

import kotlinx.html.HtmlBodyTag

/**
 * Created by stew on 7/4/17.
 */
interface SubTemplate {
   fun renderIn(root: HtmlBodyTag)
}