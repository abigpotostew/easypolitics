package bz.stewart.bracken.rest.data.bills

import bz.stewart.bracken.db.bill.data.Sponsor
import bz.stewart.bracken.shared.data.PublicSponsor

/**
 * Created by stew on 5/17/17.
 */
class DelegatedPublicSponsor(private val sponsor: Sponsor) : PublicSponsor {
    override fun getFirstname(): String {
        return sponsor.name.substringAfter(',').substringBefore(' ')
    }

    override fun getLastname(): String {
        return sponsor.name.substringBefore(',')
    }

    override fun getName(): String {
        return "${sponsor.title}. ${getFirstname()} ${getLastname()}"
    }

    override fun getSortname(): String {
        return sponsor.name
    }
}

fun Sponsor.toPublicSponsorDelegated(): PublicSponsor {
    return DelegatedPublicSponsor(this)
}