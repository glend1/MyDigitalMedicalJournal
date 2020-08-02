package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.model.ValidData
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidDataTests {
    @Test
    fun errorReport() {
        val validData = ValidData()
        validData.add("test error", false)
        val errors = validData.getErrors()
        assertTrue(errors.contains("test error"))
    }
}