/**
 * Adapted for javascript compilation from https://github.com/TinyMission/kara
 * original license: https://www.apache.org/licenses/LICENSE-2.0
 */
package kotlinx.html

enum class FormMethod() : StringEnum<FormMethod> {
    get,
    post,
    put,
    delete;

    override val value: String get() = name
}
