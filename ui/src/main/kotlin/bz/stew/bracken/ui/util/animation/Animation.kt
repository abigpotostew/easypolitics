package bz.stew.bracken.ui.util.animation

import bz.stew.bracken.ui.util.Math
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import kotlin.browser.window
import kotlin.js.Date

/**
 * Created by stew on 1/25/17.
 */
@Suppress("unused")
abstract class Animation {

    private var isRunning: Boolean = false
    private var playAnimationBackwards: Boolean = false
    private var duration: Int = 300
    protected var element: HTMLElement? = null
    private var cancellable = true
    private val onStartCallbacks: MutableList<() -> Unit> = ArrayList()
    private val onEndCallbacks: MutableList<() -> Unit> = ArrayList()
    private var currentRunId: Int = 0

    fun run() {
        runAnimation()
    }

    fun run(duration: Int,
            element: HTMLElement?) {
        this.duration = duration
        this.element = element
        runAnimation()
    }

    protected open fun onStart() {
        onStartCallbacks.forEach { callback -> callback() }
        onUpdate(if (playAnimationBackwards) 1.0 else 0.0)
    }

    protected abstract fun onUpdate(progress: Double)

    protected open fun onComplete() {
        onUpdate(if (playAnimationBackwards) 0.0 else 1.0)
        isRunning = false
        onEndCallbacks.forEach { callback -> callback() }
    }

    open fun cancel() {
        isRunning = false
    }

    fun getDuration(): Int {
        return duration
    }

    fun setDuration(duration: Int) {
        if (duration < 0) {
            throw IllegalArgumentException()
        }
        this.duration = duration
    }

    fun getPlayBackwards(): Boolean {
        return playAnimationBackwards
    }

    fun setPlayBackwards(playAnimationBackwards: Boolean) {
        this.playAnimationBackwards = playAnimationBackwards
    }

    fun getElement(): Element? {
        return element
    }

    fun setElement(element: HTMLElement?) {
        this.element = element
    }

    fun setCancellable(): Boolean {
        return cancellable
    }

    fun setCancellable(cancellable: Boolean) {
        this.cancellable = cancellable
    }

    fun addOnStartCallback(callback: () -> Unit) {
        onStartCallbacks.add(callback)
    }

    fun removeOnStartCallback(callback: () -> Unit) {
        onStartCallbacks.remove(callback)
    }

    fun clearOnStartCallbacks() {
        onStartCallbacks.clear()
    }

    fun addOnEndCallback(callback: () -> Unit) {
        onEndCallbacks.add(callback)
    }

    fun removeOnEndCallback(callback: () -> Unit) {
        onEndCallbacks.remove(callback)
    }

    fun clearOnEndCallbacks() {
        onEndCallbacks.clear()
    }

    /**
     * Rescales a number (using linear interpolation) such that the value x, which used to be between the range
     * (originalMin, originalMax) will now be proportionately between the values (newMin, newMax).
     * @param x the value to rescale.
     * *
     * @param originalMin the original minimum scale value.
     * *
     * @param originalMax the original maximum scale value.
     * *
     * @param newMin the new minimum scale value.
     * *
     * @param newMax the new maximum scale value.
     * *
     * @return x, scaled to the new range.
     */
    protected fun rescale(x: Double,
                          originalMin: Double,
                          originalMax: Double,
                          newMin: Double,
                          newMax: Double): Double {
        return (x - originalMin) * (newMax - newMin) / (originalMax - originalMin) + newMin
    }

    private fun runAnimation() {
        if (isRunning) {
            if (cancellable) cancel() else return
        }
        isRunning = true
        currentRunId++

        onStart()
        if (duration > 0) {
            requestNextAnimationFrame(currentRunId,
                    Date().getTime(),
                    duration)
        } else {
            onComplete()
        }
    }

    private fun requestNextAnimationFrame(runId: Int,
                                          startTime: Double,
                                          duration: Int) {
        // Easing not working now
        val easingInOut = false
        window.requestAnimationFrame { _ ->
            if (isRunning && currentRunId == runId) {
                val elapsedTime = (Date().getTime() - startTime)
                if (duration > elapsedTime) {
                    var progressNormal = if (!easingInOut) (elapsedTime) / duration
                    else
                        Math.easeInOutQuad(Date().getTime(),
                           startTime,
                                elapsedTime,
                                duration.toDouble())
                    //println(progress)
                    onUpdate(if (playAnimationBackwards) 1f - progressNormal else progressNormal)
                    requestNextAnimationFrame(runId,
                            startTime,
                            duration)
                } else {
                    onComplete()
                }
            }
        }
    }
}
