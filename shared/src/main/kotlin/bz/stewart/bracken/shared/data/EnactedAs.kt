package bz.stewart.bracken.shared.data

/**
 * Created by stew on 5/17/17.
 */
data class EnactedAs(val congress: String? = "",
                     val law_type: String? = "",  //public or private, usually public
                     val number: String? = "")