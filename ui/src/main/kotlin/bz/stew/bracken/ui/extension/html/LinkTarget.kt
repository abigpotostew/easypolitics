package bz.stew.bracken.ui.extension.html

enum class LinkTarget(private val value: String): HtmlConstant {
    BLANK("_blank");

    override fun toString(): String {
        return value
    }

    override fun htmlValue(): String {
        return value
    }
}

