package bz.stew.bracken.ui.common.index

/**
 * Created by stew on 2/8/17.
 */
class IndexDefinitions {
   companion object {
      private val allIndices: MutableMap<IndexEnum, MappedIndex<*, *>> = mutableMapOf()

      fun getIndex(def: IndexEnum): MappedIndex<*, *> {
         val idx = allIndices.get(def)
         if (idx != null) {
            return idx
         }
         throw NoSuchElementException("derp")
      }

      fun putIndex(newDef: IndexEnum, idx: MappedIndex<*, *>) {
         allIndices.put(newDef, idx)
      }
   }
}
