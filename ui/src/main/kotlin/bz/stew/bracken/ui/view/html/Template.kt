package bz.stew.bracken.ui.view.html

import kotlinx.html.HtmlBodyTag

/**
 * Created by stew on 3/5/17.
 */
interface Template{
   fun render(): HtmlRenderOutput
}