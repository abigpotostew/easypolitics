package bz.stewart.bracken.shared.data.person

import bz.stewart.bracken.shared.data.party.Party

/**
 * Created by stew on 1/23/17.
 */
class Legislator(private val id: Int,
                 private val name: String,
                 private val party: Party,
                 private val role: LegislatorRole,
                 private val state: String,
                 private val twitterId:String) : Person {

    override fun getId(): Int {
        return this.id
    }

    override fun getName(): String {
        return this.name
    }

    override fun getParty(): Party {
        return this.party
    }

    fun getRole(): LegislatorRole {
        return this.role
    }

    fun getTwitterId():String{
        return this.twitterId
    }
}

val EMPTY_LEG: Legislator = Legislator(-1,
                                       "",
                                       Party.DEMON,
                                       LegislatorRole.NONE,
                                       "NOSTATE",
                                       "The_Donald")

fun emptyLegislator(): Legislator {
    return EMPTY_LEG
}
