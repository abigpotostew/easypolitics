package bz.stew.bracken.ui.common.index

import bz.stew.bracken.ui.common.model.FilterEntry

/**
 * This is the base index type all use should for mapped index
 * Created by stew on 2/8/17.
 */
abstract class AbstractMappedIndex<K, I>(allKey: K?) : MappedIndex<K, I>(allKey), FilterEntry {

    fun indexInstances(allInsts: Collection<I>) {
        println("Indexing ${allInsts.size} instances in index of type "+this::class.js.name)
        for (b: I in allInsts) {
            input(map(b), b)
        }
    }

    /**
     * map an instance to it's index value
     */
    abstract fun map(inst: I): K
}