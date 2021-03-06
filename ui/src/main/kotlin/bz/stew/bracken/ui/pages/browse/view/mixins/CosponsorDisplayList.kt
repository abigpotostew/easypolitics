package bz.stew.bracken.ui.pages.browse.view.mixins

import bz.stew.bracken.ui.extension.kotlinx.HtmlFunc
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.common.view.SubTemplate
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.view.Classes
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.li
import kotlinx.html.ul

class CosponsorDisplayList (private val cosponsors: List<Legislator>): SubTemplate {
    override fun renderIn(root: FlowContent) {
        val descriptionListMap = mutableMapOf<String, HtmlFunc>()
        val cosponsorsContent: (HtmlBlockTag) -> Unit = {
            it.ul {
                for (l in cosponsors) {
                    li {
                        +l.getOfficialName()
                        +" ("
                        TwitterLink(l).renderIn(this)
                        +")"

                    }
                }
            }
        }
        descriptionListMap.put("Cosponsored by:", cosponsorsContent)

        root.horzizontalDescriptionList(descriptionListMap, Classes.boots_row)
    }
}