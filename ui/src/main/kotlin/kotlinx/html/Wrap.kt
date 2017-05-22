/**
 * Adapted for javascript compilation from https://github.com/TinyMission/kara
 * original license: https://www.apache.org/licenses/LICENSE-2.0
 */
package kotlinx.html

enum class Wrap : StringEnum<Wrap> {
    soft, hard, off;

    override val value: String get() = name
}
