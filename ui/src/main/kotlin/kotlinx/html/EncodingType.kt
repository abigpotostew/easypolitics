/**
 * Adapted for javascript compilation from https://github.com/TinyMission/kara
 * original license: https://www.apache.org/licenses/LICENSE-2.0
 */
package kotlinx.html

enum class EncodingType(override val value: String) : StringEnum<EncodingType> {
    urlencoded("application/x-www-form-urlencoded"),
    multipart("multipart/form-data"),
    plain("text/plain")
}
