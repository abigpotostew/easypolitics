package bz.stew.bracken.ui.model.index

import bz.stew.bracken.ui.controller.bill.filter.BillFilters
import bz.stew.bracken.ui.model.types.bill.BillData
import bz.stewart.bracken.shared.data.FixedStatus
import bz.stewart.bracken.shared.data.MajorStatus
import bz.stewart.bracken.shared.data.party.Party

/**
 * definitions to group bills and efficiently look up. right now it stores in backend, but that could change someday as long as this interface is used
 * Created by stew on 2/8/17.
 */

typealias BillIndex<K> = AbstractMappedIndex<K, BillData>

val STATUS_INDEX = object : BillIndex<FixedStatus>(FixedStatus.NONE) {
    override fun map(inst: BillData): FixedStatus {
        return inst.status.fixedStatus()
    }

    override fun filterType(): BillFilters {
        return BillFilters.FIXEDSTATUS
    }

    override fun allKeys(): Set<FixedStatus> {
        return super.allKeys()//forwardMap.keys.toList()
    }
}

/**
 * Simplified status
 */
val MAJOR_STATUS_INDEX = object : BillIndex<MajorStatus>(MajorStatus.NONE) {
    override fun map(inst: BillData): MajorStatus {
        return inst.billStatus().lastMajorStatus()
    }

    override fun filterType(): BillFilters {
        return BillFilters.LASTMAJORSTATUS
    }

    override fun allKeys(): Set<MajorStatus> {
        return super.allKeys()
    }
}

val PARTY_INDEX = object : BillIndex<Party>(Party.NONE) {
    override fun map(inst: BillData): Party {
        return inst.sponsor.getParty()
    }

    override fun filterType(): BillFilters {
        return BillFilters.PARTY
    }

    override fun allKeys(): Set<Party> {
        return super.allKeys()//forwardMap.keys.toList()
    }
}

val INTRO_DATE_INDEX = object : NumericDoubleAbstractMappedIndex<BillData>() {
    override fun map(inst: BillData): Double {
        return inst.intro_date.getTime()
    }

    override fun filterType(): BillFilters {
        return BillFilters.DATEINTROSTART
    }

    override fun allKeys(): Set<Double> {
        return super.allKeys()//forwardMap.keys.toList()
    }
}

val LAST_UPDATED_DATE_INDEX = object : NumericDoubleAbstractMappedIndex<BillData>() {
    override fun map(inst: BillData): Double {
        return inst.lastUpdated()
    }

    override fun filterType(): BillFilters {
        return BillFilters.LASTUPDATEDDATESTART
    }

    override fun allKeys(): Set<Double> {
        return super.allKeys()//forwardMap.keys.toList()
    }
}

@Suppress("unused")
val EMPTY_INDEX = object : AbstractMappedIndex<Any?, Any?>(null) {
    override fun filterType(): BillFilters {
        return BillFilters.IDENTITY
    }

    override fun map(inst: Any?): Any? {
        return null
    }
}

val ALL_INDEX_DEFS = listOf(STATUS_INDEX, PARTY_INDEX, INTRO_DATE_INDEX, LAST_UPDATED_DATE_INDEX, MAJOR_STATUS_INDEX)
        //mapOf<KClass<Any>, AbstractMappedIndex<Any?,Any?>>( Pair(FixedStatus::class,STATUS_INDEX) )

fun resetAllIndex() {
    for (idx in ALL_INDEX_DEFS) {
        idx.reset()
    }
}