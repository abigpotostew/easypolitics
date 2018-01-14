package bz.stewart.bracken.web.html.config

import bz.stewart.bracken.web.RequireJsDataMain
import bz.stewart.bracken.web.ScriptSrcConstants
import bz.stewart.bracken.web.html.ViewRender
import bz.stewart.bracken.web.service.WebPageContext
import kotlinx.html.*
import kotlin.reflect.KClass

class HtmlConfiguration(val tagConfigurations: Set<TagConfiguration<HTMLTag>>) {
    fun getWithTag(type: KClass<out HTMLTag>): List<TagConfiguration<HTMLTag>> {
        val out = mutableListOf<TagConfiguration<HTMLTag>>()
        for (conf in tagConfigurations) {
            if (conf.isType(type)) {
                out.add(conf)
            }
        }
        return out
    }
}

/**
 * This is used with kotlinx dsl, encapsulates setting up a tag after it has been instantiated with the dsl.
 *
 * @see WebsiteSkeleton
 */
abstract class TagConfiguration<T : Tag>(val type: KClass<T>, private val attributes: Map<String, String> = emptyMap()) {
    protected abstract fun doConfig(tag: T, context: WebPageContext)
    fun apply(tag: T, context: WebPageContext) {
        for ((k, v) in attributes.entries) {
            tag.attributes.put(k, v)
        }
        doConfig(tag, context)
    }

    fun isType(type: KClass<out HTMLTag>): Boolean {
        return this.type.equals(type)
    }
}

class BasicConfig<T : HTMLTag>(type: KClass<T>,
                               attributes: Map<String, String>,
                               val content: String? = null) : TagConfiguration<T>(type, attributes) {
    override fun doConfig(tag: T, context: WebPageContext) {
        if (this.content != null) {
            tag.text(content)
        }
    }
}

class ScriptDataMainConfig(val scriptDef: RequireJsDataMain): TagConfiguration<SCRIPT>(SCRIPT::class, emptyMap()){
    override fun doConfig(tag: SCRIPT, context: WebPageContext) {
        tag.attributes.put("data-main", scriptDef.dataMain)
        tag.src = scriptDef.src
    }
}

class ScriptConfig(scriptDef: ScriptSrcConstants) : TagConfiguration<SCRIPT>(SCRIPT::class, scriptDef.asMap()) {
    override fun doConfig(tag: SCRIPT, context: WebPageContext) {
    }
}

class ExecuteJsScriptConfig(val jsValue:String) : TagConfiguration<SCRIPT>(SCRIPT::class) {
    override fun doConfig(tag: SCRIPT, context: WebPageContext) {
        val js = this.jsValue
        tag.apply { +js }
    }
}

class TitleConfig(private val title:String):TagConfiguration<TITLE>(TITLE::class, emptyMap()){
    override fun doConfig(tag: TITLE, context: WebPageContext) {
        tag.text(this.title)
    }
}