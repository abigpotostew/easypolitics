package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.HtmlFunc
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.model.types.bill.BillSubject
import bz.stew.bracken.ui.view.html.SubTemplate
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.li
import kotlinx.html.ul

class BillSubjectsDisplayList(val billSubjects:Set<BillSubject>):SubTemplate {
    override fun renderIn(root: FlowContent) {
        if (billSubjects.isEmpty()) {
            return
        }
        val descriptionListMap = mutableMapOf<String, HtmlFunc>()
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

        root.horzizontalDescriptionList(descriptionListMap)
    }
}