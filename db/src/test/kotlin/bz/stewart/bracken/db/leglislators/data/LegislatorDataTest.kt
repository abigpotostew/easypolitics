package bz.stewart.bracken.db.leglislators.data

import org.junit.Assert.*
import org.junit.Test

class LegislatorDataTest{

    @Test
    fun testEquals() {
        val leg1 = LegislatorData(id = IdData(bioguide = "leg00A"))
        val leg1Equals = LegislatorData(id = IdData(bioguide = "leg00A"))
        val leg2 = LegislatorData(id = IdData(bioguide = "leg00A", bioguide_previous = arrayOf("previous2")))
        val leg2Equals = LegislatorData(id = IdData(bioguide = "leg00A", bioguide_previous = arrayOf("previous2")))
        val leg3 = LegislatorData(id = IdData(bioguide = "leg00A", bioguide_previous = arrayOf("previous3", "previous3.1")))
        val leg4 = LegislatorData(id = IdData(bioguide = "leg00B", bioguide_previous = arrayOf("previous3", "previous3.1")))
        val leg5 = LegislatorData(id = IdData(bioguide = "leg00A", bioguide_previous = arrayOf("previous3"), lis = "hello"), other_names = emptyList(), bio = BioData(birthday = "a"))
        val leg5Equals = LegislatorData(id = IdData(bioguide = "leg00A", bioguide_previous = arrayOf("previous3"), lis = "hello"), other_names = emptyList(), bio = BioData(birthday = "a"))

        assertEquals(leg1, leg1Equals)
        assertNotEquals(leg1, leg2)
        assertEquals(leg2, leg2Equals)
        assertNotEquals(leg2, leg3)
        assertNotEquals(leg1, leg3)
        assertNotEquals(leg3, leg4)
        assertEquals(leg5, leg5Equals)
        assertNotEquals(leg5, leg1)
        assertNotEquals(leg5, leg3)
        assertNotEquals(leg5, leg4)
    }
}