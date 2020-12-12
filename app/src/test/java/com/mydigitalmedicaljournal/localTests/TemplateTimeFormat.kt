package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import org.junit.Assert.assertEquals
import org.junit.Test

class TemplateTimeFormat {
    @Test
    fun getDate() {
        assertEquals(DataTime.TimeFormat.getType(R.id.date), DataTime.TimeFormat.DATE)
    }

    @Test
    fun getDateTime() {
        assertEquals(DataTime.TimeFormat.getType(R.id.date_time), DataTime.TimeFormat.DATETIME)
    }

    @Test
    fun getDuration() {
        assertEquals(DataTime.TimeFormat.getType(R.id.duration), DataTime.TimeFormat.DURATION)
    }
}