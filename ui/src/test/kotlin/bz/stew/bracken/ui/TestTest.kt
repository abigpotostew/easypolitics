package bz.stew.bracken.ui

import kotlin.test.assertEquals

/**
 * Shows that tests for JavaScript-specific functionality will run at the same time as tests for common functionality.
 */
class TestTest {
    @kotlin.test.Test
    fun stringRepresentationIsCorrect() {

        assertEquals("Pizza", "Pizza")
    }
}
