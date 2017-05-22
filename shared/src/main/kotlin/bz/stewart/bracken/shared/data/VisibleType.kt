package bz.stewart.bracken.shared.data

/**
 * For enum classes with an ordinal per instance. To help with selecting the item with both an integer and a nice visible name
 * Created by stew on 1/25/17.
 */
interface VisibleType {

    fun lowercaseName(): String
    fun capitalizedName(): String
    fun niceFormat(): String
    fun shortLabel(): String
    fun shortCode():String
}