package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.template.TemplateEnum
import org.junit.Assert.assertEquals
import org.junit.Test

class MainTemplateEnumTest {
    @Test
    fun testLayoutList() {
        assertEquals(TemplateEnum.layoutList.size, 9)
    }

    @Test
    fun testNameList() {
        assertEquals(TemplateEnum.nameList.size, 6)
    }
}