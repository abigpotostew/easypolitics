package bz.stew.bracken.ui.service

interface Parser <T> {
    fun parse(data:dynamic):Collection<T>
}