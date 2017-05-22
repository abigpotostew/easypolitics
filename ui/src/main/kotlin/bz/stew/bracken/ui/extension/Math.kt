package bz.stew.bracken.ui.extension

import bz.stew.bracken.ui.util.log.Log

/**
 * Created by stew on 2/11/17.
 */

/**
 * does not handle NaN or infinity
 */
fun clamp(v: Double,
               lo: Double,
               hi: Double): Double {
    return if (v < lo) lo else if (v > hi) hi else v
}

/**
 * tries to work with NaN
 */
fun niceClamp(v: Double,
                   lo: Double,
                   hi: Double,
                   preferLo: Boolean): Double {
    Log.debug{"v: $v, lo: $lo, hi: $hi, preferLo: $preferLo"}//println("v: $v, lo: $lo, hi: $hi, preferLo: $preferLo")
    if (v.isNaN()) {
        if (preferLo) return lo else return hi
    }
    return if (v < lo) lo else if (v > hi) hi else v
}
