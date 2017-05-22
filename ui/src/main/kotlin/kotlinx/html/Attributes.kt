/**
 * Adapted for javascript compilation from https://github.com/TinyMission/kara
 * original license: https://www.apache.org/licenses/LICENSE-2.0
 */
package kotlinx.html

object Attributes {
    val id = IdAttribute("id")
    val style = StringAttribute("style")

    val title = IdAttribute("title")
    val href = LinkAttribute("href")
    val cite = LinkAttribute("cite")
    val rel = StringAttribute("rel")
    val target = StringAttribute("target")
    val mimeType = MimeAttribute("type")
    val width = IntAttribute("width")
    val height = IntAttribute("height")
    val action = LinkAttribute("action")
    val enctype = EnumAttribute("enctype", EncodingType::class, EncodingType.values())
    val method = EnumAttribute("method", FormMethod::class, FormMethod.values())
    val src = LinkAttribute("src")
    val alt = TextAttribute("alt")
    val autocomplete = BooleanAttribute("autocomplete", "on", "off")
    val autofocus = TickerAttribute("autofocus")
    val novalidate = TickerAttribute("novalidate")
    val checked = TickerAttribute("checked")
    val disabled = TickerAttribute("disabled")
    val maxlength = IntAttribute("maxlength")
    val multiple = TickerAttribute("multiple")
    val inputType = EnumAttribute("type", InputType::class, InputType.values())
    val buttonType = EnumAttribute("type", ButtonType::class, ButtonType.values())
    val name = StringAttribute("name")
    val pattern = RegexpAttribute("pattern")
    val placeholder = TextAttribute("placeholder")
    val readonly = TickerAttribute("readonly")
    val required = TickerAttribute("required")
    val size = IntAttribute("size")
    val step = IntAttribute("step")
    val value = StringAttribute("value")
    val forId = IdAttribute("for")
    val label = TextAttribute("label")
    val selected = TickerAttribute("selected")
    val cols = IntAttribute("cols")
    val rows = IntAttribute("rows")
    val wrap = EnumAttribute("wrap", Wrap::class, Wrap.values())
}
