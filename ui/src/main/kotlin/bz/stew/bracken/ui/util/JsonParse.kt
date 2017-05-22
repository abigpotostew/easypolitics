package bz.stew.bracken.ui.util

/**
 * Created by stew on 1/24/17.
 */

//@native("$")  fun jq(window: Window): JQuery = JQuery();

class JsonUtil {


    companion object Parse {
        fun parse(string: String): dynamic {
            val out =JSON.parse<dynamic>(string)
            return out
            //return JSON.parse<dynamic>(string)
            //return jq().parseJSON(string)

        }

        /**
         * get a more type accurate string from a native js string
         */
        fun niceString(str: String): String {
            return "".plus(str)
        }
    }
}