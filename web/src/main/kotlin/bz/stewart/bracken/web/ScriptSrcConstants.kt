package bz.stewart.bracken.web

import bz.stewart.bracken.web.html.ConstantText

/**
 * For local src ref, the root dir starts with static
 */
enum class ScriptSrcConstants(private val value: String,
                              private val integrity: String = emptyString(),
                              private val crossorigin: String = emptyString()) : ConstantText {

    JQUERY_MIN_EXT("https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"),
    JQUERY_ACTUAL_LOCAL("/jquery.actual.js"),
    VELOCITY_EXT("https://cdnjs.cloudflare.com/ajax/libs/velocity/1.4.0/velocity.min.js"),
    VELOCITY_UI_MIN_EXT("https://cdn.jsdelivr.net/velocity/1.4.0/velocity.ui.min.js"),
    TETHER_MIN_EXT("https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js", "sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" ,"anonymous"),
    BOOSTRAP_MIN_EXT("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js", "sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn", "anonymous"),

    KOTLIN_JS_FROM_UI_LOCAL("/lib/lib/kotlin.js"), //todo squish with ui code
    KOTLINX_JS_FROM_UI_LOCAL("/lib/lib/kotlinx-html-js.js"), //must be before easypolitics-ui.js
    EASYPOLITICS_UI_JS_FROM_UI_LOCAL("/lib/easypolitics-ui.js"),// todo add integrity
    ;

    override fun get(): String {
        return this.value
    }

    fun asMap():Map<String,String>{
        val out = mutableMapOf<String,String>()
        out.put(HtmlConstants.SRC.get(), this.value)
        if(this.integrity!= emptyString())
            out.put(HtmlConstants.INTEGRITY.get(), this.integrity)
        if(this.crossorigin!= emptyString())
            out.put(HtmlConstants.CROSSORIGIN.get(), this.crossorigin)
        return out
    }
}

inline fun emptyString(): String = ""
