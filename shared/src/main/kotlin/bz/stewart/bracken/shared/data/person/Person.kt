package bz.stewart.bracken.shared.data.person

import bz.stewart.bracken.shared.data.party.Party

/**
 * Created by stew on 1/23/17.
 */
interface Person {
    fun getId(): Int
    fun getName(): String
    fun getParty(): Party
}