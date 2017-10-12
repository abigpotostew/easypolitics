package bz.stewart.bracken.web.extension

import kotlinx.html.FlowOrPhrasingOrMetaDataContent
import kotlinx.html.SCRIPT

/**
 * doesn't work :(
 */
fun FlowOrPhrasingOrMetaDataContent.script(type: String? = null,
                                           src: String? = null,
                                           integrity: String? = null,
                                           crossorigin: String?,
                                           block: (SCRIPT.() -> Unit)? = null): Unit {
    val initialAttr = mutableMapOf<String, String>()
    if (integrity != null)
        initialAttr.put("integrity", integrity)
    if (crossorigin != null)
        initialAttr.put("crossorigin", crossorigin)
    if (type != null)
        initialAttr.put("type", type)
    if (src != null)
        initialAttr.put("src", src)
    val script = SCRIPT(initialAttr, this.consumer)
    block?.invoke(script)

}