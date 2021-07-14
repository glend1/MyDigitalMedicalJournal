package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import org.junit.Assert
import org.junit.Test

class TemplateDataTests {
    @Test
    fun testName() {
        Assert.assertEquals(DataName(Utils.CONTEXT).type, TemplateEnum.NAME)
    }

    @Test
    fun testTime() {
        Assert.assertEquals(DataTime(Utils.CONTEXT).type, TemplateEnum.TIME)
    }
}