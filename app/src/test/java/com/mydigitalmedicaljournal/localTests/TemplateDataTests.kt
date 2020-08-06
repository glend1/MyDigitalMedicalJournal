package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.data.DataName
import com.mydigitalmedicaljournal.template.data.DataTime
import org.junit.Assert.assertEquals
import org.junit.Test

class TemplateDataTests {
    @Test
    fun testName() {
        assertEquals(DataName().type, TemplateEnum.NAME)
    }

    @Test
    fun testTime() {
        assertEquals(DataTime().type, TemplateEnum.TIME)
    }
}