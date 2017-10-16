package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.view.html.Classes
import bz.stew.bracken.ui.view.html.SubTemplate
import bz.stew.bracken.ui.view.html.bootstrap.Bill
import kotlinx.html.HtmlBlockTag
import kotlinx.html.HtmlBodyTag
import kotlinx.html.p

/**
 * Created by stew on 7/4/17.
 */
class BillDetailsTab(private val template: Bill) : SubTemplate {
    override fun renderIn(root: HtmlBlockTag) {
        val cosponsors = template.billView.billData.cosponsors
        root.p(Classes.billCosponsors) {
            val descriptionListMap = mutableMapOf<String, (HtmlBodyTag) -> Unit>()
            val cosponsors: (HtmlBodyTag) -> Unit = {
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
                val subjectView: (HtmlBodyTag) -> Unit = {
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