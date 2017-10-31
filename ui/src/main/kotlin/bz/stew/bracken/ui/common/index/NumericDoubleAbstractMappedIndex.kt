package bz.stew.bracken.ui.common.index

import bz.stew.bracken.ui.util.log.Log

/**
 * Created by stew on 2/10/17.
 */
abstract class NumericDoubleAbstractMappedIndex<I> : AbstractMappedIndex<Double, I>(null) {

    private var minKeyCached: Double? = null
    private var maxKeyCached: Double? = null

    fun instancesByOperator(operator: IndexOperation, c: Double): Collection<I> {
        Log.debug({ "$operator comparing $c in map \n\t[[[$forwardMap]]]" })
        val out = when (operator) {
            IndexOperation.GreaterThanOrEqual ->
                //forwardMap.filterKeys { compareTo(it,instancesWith(c))>=0 }.flattenValueSets()
                forwardMap.flatMap { Log.debug { ("Comparing $c to ${it.key}") }; if (it.key-c>=0.0) {
                    Log.debug({ "--> PASSED @ ${it.key}" })
                    it.value
                    } else {
                        Log.debug { "--> FAILED @ ${it.key}" }
                        val emptyList: List<I> = emptyList()
                        emptyList
                    }
                }
            IndexOperation.LessThanOrEqual ->
                //forwardMap.filterKeys { compareTo(it,instancesWith(c))>=0 }.flattenValueSets()
                forwardMap.flatMap { Log.debug { ("Comparing $c to ${it.key}") }; if (it.key-c<=0.0) {
                    Log.debug({ "--> PASSED @ ${it.key}" })
                    it.value
                } else {
                    Log.debug { "--> FAILED @ ${it.key}" }
                    emptyList<I>()
                    //emptyListTyped<I>()
                }
                }
        }
        Log.debug({ "OUT == [[[\n\t$out]]]" })
        return out
    }

    /**
     * 0 is equal
     */
    fun compareTo(key: Double, other: I): Double {
        return key - map(other)
    }

    fun maxKey(): Double {
        if (this.maxKeyCached == null) {
            this.maxKeyCached = forwardMap.keys.max() ?: Double.NaN
        }
        return this.maxKeyCached ?: Double.NaN
    }

    fun minKey(): Double {
        if (this.minKeyCached == null) {
            this.minKeyCached = forwardMap.keys.min() ?: Double.NaN
        }
        return this.minKeyCached ?: Double.NaN
    }

}