package bz.stew.bracken.ui.extension.kotlin.collections

/**
 * Created by stew on 2/10/17.
 */
fun <K, V> Map<K, Set<V>>.flattenValueSets(): List<V> {
    val out : List<V> = mutableListOf()
    for (set in this.values) {
        out.plus(set)
    }
    return out
}

fun <L> Collection<L>.flatten(): Collection<L> {
    val out: List<L> = mutableListOf()
    for (set in this) {
        out.plus(set)
    }
    return out
}