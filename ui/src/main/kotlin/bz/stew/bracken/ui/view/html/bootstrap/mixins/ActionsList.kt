package bz.stew.bracken.ui.view.html.bootstrap.mixins

import bz.stew.bracken.ui.extension.kotlinx.DlTitleFunc
import bz.stew.bracken.ui.extension.kotlinx.HtmlFunc
import bz.stew.bracken.ui.extension.kotlinx.horzizontalDescriptionList
import bz.stew.bracken.ui.extension.kotlinx.p
import bz.stew.bracken.ui.model.types.bill.BillAction
import bz.stew.bracken.ui.view.html.SubTemplate
import kotlinx.html.FlowContent

class ActionsList(private val actionsList: Set<BillAction>) : SubTemplate {
    override fun renderIn(root: FlowContent) {
        val descriptionListMap: MutableMap<DlTitleFunc, HtmlFunc> = mutableMapOf()

        for (action in actionsList) {
            descriptionListMap.put({

                action.actedAtDate().toString()
            },
                    {
                        it.p {
                            +action.getText()
                        }
                    })
        }
        root.horzizontalDescriptionList(descriptionListMap)
//            .associate {
//            return Pair<String, (HtmlBodyTag) -> Unit>(it.actedAtDate().toString(),{
//                {
//                    val action = it
//                    return {
//
//                        +action.getText()
//                    }
//                }
//            })
//        }
    }
}