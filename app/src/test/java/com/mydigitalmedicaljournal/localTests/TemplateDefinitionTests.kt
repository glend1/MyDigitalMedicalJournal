package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateDefinitionTests {
    private lateinit var templateDefinition: TemplateDefinition
    private val templateId = UUID.randomUUID()

    @Before
    fun setup() {
        templateDefinition = TemplateDefinition(templateId)
    }

    @Test
    fun testId() {
        assertEquals(templateDefinition.getId(), templateId)
    }

    @Test
    fun testName() {
        val newName = "test"
        templateDefinition.name = newName
        assertEquals(templateDefinition.name, newName)
    }

    @Test
    fun testTime() {
        val newTime = DataTime.TimeFormat.DATE
        templateDefinition.time = newTime
        assertEquals(templateDefinition.time, newTime)
    }

    @Test
    fun validate() {
        templateDefinition.name = "test"
        templateDefinition.time = DataTime.TimeFormat.DATE
        val errors = templateDefinition.validate().getErrors()
        assertEquals(errors.size, 0)
    }
}