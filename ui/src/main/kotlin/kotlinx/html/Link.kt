/**
 * Adapted for javascript compilation from https://github.com/TinyMission/kara
 * original license: https://www.apache.org/licenses/LICENSE-2.0
 */
package kotlinx.html

interface Link {
    fun href(): String
}

class DirectLink(val hrefIn: String) : Link {
    override fun href() = hrefIn
}
