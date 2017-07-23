package bz.stew.bracken.ui.model.index

/**
 * <K> is the key
 * <I> is the large number of instances mapped to the key
 * Created by stew on 2/8/17.
 */
abstract class MappedIndex<K, I> (private val allKey: K?) {

    protected val forwardMap: MutableMap<K, MutableSet<I>> = mutableMapOf()
    protected val reverseMap: MutableMap<I, K> = mutableMapOf()

    //private var dirty:Boolean = false
    //private var instancesWithCache:Map<C,Collection<I>> = mapOf()

    /**
     * put instance in map at this key
     */
    fun input(key: K, instance: I) {
        //println("<$key, $i>")
        var insts = forwardMap.get(key)
        if (insts == null) {
            insts = mutableSetOf()
        }
        if (!insts.contains(instance)) {
            insts.add(instance)
            //dirty = true
        }
        forwardMap.put(key, insts)
        reverseMap.put(instance, key)
    }

    fun instancesWith(c: K): Collection<I> {
        if (c == allKey) {
            return reverseMap.keys.toList()
        }
        val insts = forwardMap.get(c)
        return insts ?.toList() ?: emptyList<I>()
    }

    open fun allKeys(): Set<K> {
        return forwardMap.keys.union(setOf())
        //TODO cache this list
    }

    fun reset() {
        forwardMap.clear()
        reverseMap.clear()
    }
}