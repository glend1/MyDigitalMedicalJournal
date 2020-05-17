package com.mydigitalmedicaljournal

import androidx.test.core.app.launchActivity
import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {
    @Test
    fun testEvent() {
        val scenario = launchActivity<MainActivity>()
        assertNotNull(scenario)
    }
}
