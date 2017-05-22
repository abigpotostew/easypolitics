package bz.stew.bracken.ui.util

/**
 * Created by stew on 1/28/17.
 */

class Math {

    //Call these with Math.easeInOutQuad()
    companion object Ease {
        fun easeInOutQuad(t: Double,
                          b: Double,
                          c: Double,
                          d: Double): Double {
            var ti: Double = t / (d / 2)
            if (t < 1) return c / 2 * ti * ti + b;
            ti--;
            return -(c.toDouble()) / 2 * (ti * (t - 2) - 1) + b;
        }

    }
}

