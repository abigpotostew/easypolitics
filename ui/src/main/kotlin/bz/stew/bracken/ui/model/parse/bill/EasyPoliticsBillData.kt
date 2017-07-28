package bz.stew.bracken.model.parse.bill

import bz.stew.bracken.ui.extension.html.jsParseDate
import bz.stew.bracken.ui.model.parse.bill.BillDataBuilder
import bz.stew.bracken.ui.model.parse.bill.EasyPoliticsMajorAction
import bz.stew.bracken.ui.model.parse.bill.EasyPoliticsParser
import bz.stew.bracken.ui.model.types.bill.BillAction
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stew.bracken.ui.model.types.bill.BillSubject
import bz.stew.bracken.ui.model.types.bill.status.BillStatusData
import bz.stew.bracken.ui.util.log.Log
import bz.stewart.bracken.shared.data.BillResolutionType
import bz.stewart.bracken.shared.data.BillType
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.TypeHelperDefaults
import bz.stewart.bracken.shared.data.VisibleTypeMatcher
import bz.stewart.bracken.shared.data.matchVisibleType
import bz.stewart.bracken.shared.data.party.Party
import bz.stewart.bracken.shared.data.person.Legislator
import bz.stewart.bracken.shared.data.person.emptyLegislator

@Suppress("UnsafeCastFromDynamic")
/**
 * Created by stew on 4/29/17.
 */
class EasyPoliticsBillData(private val parser: EasyPoliticsParser) : BillDataBuilder {

    private fun resolveMajorActions(
        actionsArr: dynamic): Collection<EasyPoliticsMajorAction> {
        val out = mutableListOf<EasyPoliticsMajorAction>()
        val numActions: Int = actionsArr
        for (i in 0..numActions) {
            val action: dynamic = actionsArr

            try {
                val datetime: String = action["acted_at"]
                val actionType: String = action["type"]
                val textDescription = action["text"]
                val actionObj = EasyPoliticsMajorAction(textDescription, actionType, datetime
                )
                out.add(actionObj)
            } catch (e: Exception) {
                error("error parsing this action: ${e.toString()}")
            }
        }
        return out
    }

    private fun resolveLegislator(sponsor: dynamic): Legislator? {
        val s = sponsor
        if (s == null) {
            return null
        }
        val cachedLegislator: Legislator? = parser.legislatorCached(s.bioguideId)
        if (cachedLegislator != null) {
            return cachedLegislator
        }
        val parsed = Legislator(bioguideId = s.bioguideId,
            firstName = s.firstName,
            lastName = s.lastName,
            middleName = s.middleName,
            officialName = s.officialName,
            nickName = s.nickName,
            party = matchVisibleType(Party.values(), s.party,
                VisibleTypeMatcher.CAPS),
            role = TypeHelperDefaults.defaultRoleTypeMatcher(s.role),
            state = s.state,
            twitterId = s.twitter,
            phoneNumber = s.phoneNumber,
            website = s.website)
        parser.saveLegislator(parsed)
        return parsed
    }

    private fun resolveCosponsors(cosponsors: dynamic): List<Legislator> {
        if (cosponsors == null) {
            println("nope")
            return emptyList()
        }
        val out = mutableListOf<Legislator>()
        val numCosponsor: Int = cosponsors.length
        for (i in 0..numCosponsor) {
            val data = cosponsors[i]
            if (data != null) {
                val p = resolveLegislator(data)
                out.add(p ?: emptyLegislator())
            }
        }
        return out
    }

    private fun resolveSubjects(subjects: dynamic): Set<BillSubject> {
        if (!subjects) {
            return emptySet()
        }
        val out = mutableSetOf<BillSubject>()
        val len = subjects.length
        for (i in 0..len) {
            val subject: String = subjects[i]
            out.add(BillSubject(subject))
        }
        return out
    }

    private fun resolveActions(actionsArr: dynamic):Set<BillAction>{
        if(!actionsArr) {
            return emptySet()
        }
        val out = mutableSetOf<BillAction>()
        val len = actionsArr.length
        for(i in 0..len){
            val actionDyn = actionsArr[i]
            if(actionDyn== undefined){
                continue
            }
            val action = BillAction(actionDyn)
            out.add(action)
        }
        return out
    }

    override fun build(govInput: dynamic): BillData {
        val gi = govInput
        val title = gi.officialTitle
        val shortTitle = gi.billName
        val uniqueParsedId: String = gi.billId
        val uniqueId: Int = uniqueParsedId.hashCode()
        val congress: Int = gi.congress
        val subjectsArrDyn = gi.subjects

        //val btVals = BillType.values()
        val bill_type: BillType = TypeHelperDefaults.defaultBillTypeMatcher(gi.billType)
        val bill_res_type = matchVisibleType(BillResolutionType.values(), gi.resolutionType,
            VisibleTypeMatcher.LOWER)

        val majorActions = resolveMajorActions(gi.actions)
        val resolvedFixedStatus = FixedStatus.valueOfDb(gi.currentStatus)

        //todo status label and description not entered

        val currentStatus = BillStatusData(fixedStatus = resolvedFixedStatus,
            date = jsParseDate(gi.currentStatusAt),
            majorActions = majorActions,
            description = gi.currentStatusDescription,
            label = gi.currentStatusLabel)

        val number = (gi.number as String).toInt()

        val link = gi.url
        val is_alive = true
        val is_current = true
        val intro_date = jsParseDate(gi.introducedAt) // todo this is just a number now

        val parsedSponsor = resolveLegislator(gi.sponsor)
        if (parsedSponsor == null) {
            Log.error("sponsor is null for bill: $uniqueParsedId of congress $congress")
        }
        val sponsor = parsedSponsor ?: emptyLegislator()

        val cosponsors = resolveCosponsors(gi.cosponsors)

        val topSubject: String = gi.subjectsTopTerm ?: ""
        val subjects = resolveSubjects(subjectsArrDyn)

        val actions = resolveActions(gi.actions)

        return BillData(
            officialTitle = title,
            shortTitle = shortTitle,
            uniqueId = uniqueId,
            congress = congress,
            bill_type = bill_type,
            bill_resolution_type = bill_res_type,
            currentStatus = currentStatus,
            number = number,
            link = link,
            intro_date = intro_date,
            sponsor = sponsor,
            cosponsors = cosponsors,
            origData = gi,
            subjects = subjects,
            subjectsTopTerm = topSubject,
            actions = actions
        )
    }
}