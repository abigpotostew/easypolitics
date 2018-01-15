package bz.stew.bracken.ui.id

/**
 * Base64 encode and decode, cross browser friendly.
 *
 * http://jsfiddle.net/gabrieleromanato/qAGHT/
 */
class Base64 private constructor() {
    companion object {
        const val KEY_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="

        fun encode(input: String): String {
            var output = ""
            var chr1: Int
            var chr2: Int
            var chr3: Int
            var enc1: Int
            var enc2: Int
            var enc3: Int
            var enc4: Int
            var i = 0

            val input: String = Base64._utf8_encode(input)

            while (i < input.length) {
                chr1 = jsCharCodeAt(input, i++)
                chr2 = jsCharCodeAt(input, i++)
                chr3 = jsCharCodeAt(input, i++)

                enc1 = chr1.shr(2)
                enc2 = ((chr1.and(3)).shl(4)).or(chr2.shr(4))
                enc3 = ((chr2.and(15)).shl(2)).or(chr3.shr(6))
                enc4 = chr3.and(63)

                if (chr2.toDouble().isNaN()) {
                    enc3 = 64
                    enc4 = 64
                } else if (chr3.toDouble().isNaN()) {
                    enc4 = 64
                }
                output = output + KEY_STR.get(enc1) + KEY_STR.get(enc2) + KEY_STR.get(enc3) + KEY_STR.get(enc4)
            }
            return output
        }

        fun decode(inStr: String): String {
            var input = inStr
            var output = ""
            var chr1: Int
            var chr2: Int
            var chr3: Int
            var enc1: Int
            var enc2: Int
            var enc3: Int
            var enc4: Int
            var i = 0

            val pattern = Regex("[^A-Za-z0-9\\+\\/\\=]")
            input = input.replace(pattern, "")

            while (i < input.length) {
                enc1 = KEY_STR.indexOf(input.get(i++))
                enc2 = KEY_STR.indexOf(input.get(i++))
                enc3 = KEY_STR.indexOf(input.get(i++))
                enc4 = KEY_STR.indexOf(input.get(i++))

                chr1 = (enc1.shl(2)).or(enc2.shr(4))
                chr2 = ((enc2.and(15)).shl(4)).or(enc3.shr(2))
                chr3 = ((enc3.and(3)).shr(6)).or(enc4)

                output += jsFromCharCode(chr1)

                if (enc3 != 64) {
                    output += jsFromCharCode(chr2)
                }
                if (enc4 != 64) {
                    output += jsFromCharCode(chr3)
                }
            }
            output = utf8_decode(output)
            return output

        }

        private fun _utf8_encode(inString: String): String {
            val pattern = Regex("\\r\\n")
            var string = inString.replace(pattern, "\n")
            var utftext = ""

            for (n in (0..string.length - 1)) {
                var c = jsCharCodeAt(string, n)
                if (c < 128) {
                    utftext += jsFromCharCode(c)
                } else if ((c > 127) && (c < 2048)) {
                    utftext += jsFromCharCode((c.shr(6)).or(192))
                    utftext += jsFromCharCode((c.and(63)).or(128))
                } else {
                    utftext += jsFromCharCode((c.shr(12)).or(224))
                    utftext += jsFromCharCode(((c.shr(6)).and(63)).or(128))
                    utftext += jsFromCharCode((c.and(63)).or(128))
                }
            }
            return utftext
        }

        private fun utf8_decode(utftext: String): String {
            var string = ""
            var i = 0
            var c: Int
            var c2: Int
            var c3: Int

            while (i < utftext.length) {
                c = jsCharCodeAt(utftext, i)
                if (c < 128) {
                    string += jsFromCharCode(c)
                    i++
                } else if ((c > 191) && (c < 224)) {
                    c2 = jsCharCodeAt(utftext, i + 1)
                    string += jsFromCharCode(((c.and(31)).shl(6)).or((c2.and(63))))
                    i += 2
                } else {
                    c2 = jsCharCodeAt(utftext, i + 1)
                    c3 = jsCharCodeAt(utftext, i + 2)
                    string += jsFromCharCode(((c.and(15)).shl(12)).or(((c2.and(63)).shl(6)).or((c3.and(63)))))
                    i += 3
                }
            }
            return string
        }

        private fun jsCharCodeAt(string: String, idx: Int): Int {
            return js("string.charCodeAt(idx)")
        }

        private fun jsFromCharCode(num: Int): String {
            return js("String.fromCharCode(num)")
        }
    }
}