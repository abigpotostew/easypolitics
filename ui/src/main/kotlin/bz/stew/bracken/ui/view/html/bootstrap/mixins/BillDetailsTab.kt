package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.ac
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.ul

/**
 * Created by stew on 7/4/17.
 */
class BillDetailsTab(private val template: Bill) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val cosponsors = template.billView.billData.cosponsors
        root.p{
            ac(Classes.billCosponsors)
            val descriptionListMap = mutableMapOf<String, (HtmlBlockTag) -> Unit>()
            val cosponsors: (HtmlBlockTag) -> Unit = {
                it.ul {
                    for (l in cosponsors) {
                        li {
                            +l.getOfficialName()
                            +" ("
                            TwitterLink(l).renderIn(this)
                            +")"
//                            a {
//                                +l.getOfficialName()
//                            }

                        }
                    }
                }
            }
            descriptionListMap.put("Cosponsored by:", cosponsors)
            val billSubjects = template.billView.billData.subjects
            if (undefined != billSubjects && !billSubjects.isEmpty()) {
                val subjectView: (HtmlBlockTag) -> Unit = {
                    it.ul {
                        for (subject in billSubjects) {
                            if (subject.subject != undefined) {
                                li {
                                    +subject.subject
                                }
                            }
                        }
                    }
                }
                descriptionListMap.put("Subjects: ", subjectView)
            }
            val actions = template.billView.billData.actions
            if(!actions.isEmpty()){

            }

            this.horzizontalDescriptionList(descriptionListMap)

            ActionsList(template.billView.billData.actions).renderIn(root)
        }
        //todo show the history and amendments of the bill
    }
}