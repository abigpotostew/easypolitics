package bz.stewart.bracken.shared.data

/**
 * Created by stew on 4/29/17.
 */


enum class VisibleTypeMatcher(val func : VisibleType.()->String){

   CAPS(VisibleType::capitalizedName),
   LOWER(VisibleType::lowercaseName),
   SHORTCODE(VisibleType::shortCode);

   fun matches(value:String, item:VisibleType):Boolean{
      return item.func().equals(value)
   }
}

/**
 * Matching the string value to the property defined by the matcher.
 */
fun <T :VisibleType> matchVisibleType(values: Array<T>,
                matchTo: String, matcher: VisibleTypeMatcher): T {
   for (item: T in values) {
      if (matcher.matches(matchTo,item)) {
         return item
      }
   }
   throw BadStateException("Can't match type ${values[0]::class} with name = '$matchTo'")
}

fun defaultBillTypeMatcher(matchTo:String):BillType{
   return matchVisibleType(BillType.values(),matchTo,VisibleTypeMatcher.SHORTCODE)
}