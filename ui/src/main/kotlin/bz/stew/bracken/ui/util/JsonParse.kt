package bz.stew.bracken.ui.util

/**
 * Created by stew on 1/24/17.
 */

class JsonUtil {

    companion object Parse {
        fun parse(string: String): dynamic {
            return JSON.parse(string)
        }

        /**
         * get a more type accurate string from a native js string
         */
        fun niceString(str: String): String {
            return "".plus(str)
        }
    }
}