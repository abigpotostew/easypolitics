package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.ConstantText

/**
 * For local src ref, the root dir starts with static
 */
enum class ScriptSrcConstants(val jsName:String,
                              val src: String,
                              private val integrity: String = emptyString(),
                              private val crossorigin: String = emptyString()) : ConstantText {

    JQUERY_MIN_EXT("jquery","https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"),
    JQUERY_ACTUAL_LOCAL("jqueryActual","/lib/jquery.actual.js"),
    VELOCITY_EXT("velocity","https://cdnjs.cloudflare.com/ajax/libs/velocity/1.4.0/velocity.min.js"),
    VELOCITY_UI_MIN_EXT("velocityUi","https://cdn.jsdelivr.net/velocity/1.4.0/velocity.ui.min.js"),
    TETHER_MIN_EXT("tether","https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js", "sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" ,"anonymous"),
    BOOSTRAP_MIN_EXT("bootstrap","https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js", "sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn", "anonymous"),

    //REQUIREJS_LOCAL("require","/lib/require.js"), //todo squish with ui code
    KOTLIN_JS_FROM_UI_LOCAL("kotlin","lib/lib/kotlin"), //todo squish with ui code
    KOTLINX_JS_FROM_UI_LOCAL("kotlinHtmlJs","lib/lib/kotlinx-html-js"), //must be before easypolitics-ui.js
    EASYPOLITICS_UI_JS_FROM_UI_LOCAL("easypoliticsUi","lib/easypolitics-ui"),// todo add integrity
    ;

    override fun get(): String {
        return this.src
    }

    fun asMap():Map<String,String>{
        val out = mutableMapOf<String,String>()
        out.put(HtmlConstants.SRC.get(), this.src)
        if(this.integrity!= emptyString())
            out.put(HtmlConstants.INTEGRITY.get(), this.integrity)
        if(this.crossorigin!= emptyString())
            out.put(HtmlConstants.CROSSORIGIN.get(), this.crossorigin)
        return out
    }
}

inline fun emptyString(): String = ""
