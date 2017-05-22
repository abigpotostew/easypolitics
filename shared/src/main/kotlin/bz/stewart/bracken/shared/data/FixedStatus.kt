package bz.stewart.bracken.shared.data

import bz.stewart.bracken.shared.Indexed

/**
 * Created by stew on 2/8/17.
 */
//todo share this with rest/db
enum class FixedStatus(val lastMajorCompletedStatus: MajorStatus = MajorStatus.NONE) : Indexed {
    NONE,
    prov_kill_veto(MajorStatus.FAILED),
    fail_second_senate(MajorStatus.FAILED),
    passed_bill(MajorStatus.PASSED_SENATE),
    passed_constamend(MajorStatus.PASSED_SENATE),
    pass_back_senate(MajorStatus.PASSED_SENATE),
    vetoed_override_fail_second_house(MajorStatus.FAILED),
    fail_originating_house(MajorStatus.FAILED),
    fail_second_house(MajorStatus.FAILED),
    override_pass_over_house(MajorStatus.PASSED_HOUSE),
    override_pass_over_senate(MajorStatus.PASSED_SENATE),
    pass_back_house(MajorStatus.PASSED_HOUSE),
    prov_kill_cloturefailed(MajorStatus.FAILED),
    enacted_veto_override(MajorStatus.LAW),
    passed_concurrentres(MajorStatus.PASSED_SENATE),
    prov_kill_suspensionfailed(MajorStatus.FAILED),
    passed_simpleres(MajorStatus.PASSED_SENATE),
    vetoed_pocket(MajorStatus.FAILED),
    vetoed_override_fail_originating_house,
    conference_passed_senate(MajorStatus.PASSED_SENATE),
    fail_originating_senate(MajorStatus.FAILED),
    pass_over_senate(MajorStatus.PASSED_SENATE),
    prov_kill_pingpongfail(MajorStatus.FAILED),
    enacted_signed(MajorStatus.LAW),
    pass_over_house(MajorStatus.PASSED_HOUSE),
    conference_passed_house(MajorStatus.PASSED_HOUSE),
    reported(MajorStatus.INTRODUCED),
    vetoed_override_fail_second_senate(MajorStatus.FAILED),
    vetoed_override_fail_originating_senate(MajorStatus.FAILED),
    enacted_tendayrule(MajorStatus.LAW),
    introduced(MajorStatus.INTRODUCED),
    enacted_unknown(MajorStatus.LAW),
    referred(MajorStatus.INTRODUCED);

    companion object {
        fun valueAt(i: Int): FixedStatus {
            for (fs: FixedStatus in FixedStatus.values()) {
                if (fs.ordinal == i) {
                    return fs
                }
            }
            throw IllegalArgumentException("Can't convert int of value $i to FixedStatus enum.")
        }
        fun valueOfDb(dbStatus:String): FixedStatus {
           val cleanStatus = dbStatus.replace(':','_').toLowerCase()
           return FixedStatus.valueOf(cleanStatus)
        }
    }
}