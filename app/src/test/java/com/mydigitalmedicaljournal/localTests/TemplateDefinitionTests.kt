package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.template.fields.data.DataTest
import com.mydigitalmedicaljournal.template.fields.data.DataTime
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
        templateDefinition.setName(newName)
        assertEquals(templateDefinition.getName(), newName)
    }

    @Test
    fun testTime() {
        val newTime = DataTime.TimeFormat.DATE
        templateDefinition.setTime(newTime)
        assertEquals(templateDefinition.getTime(), newTime)
    }

    @Test
    fun passValidation() {
        templateDefinition.setName("test")
        templateDefinition.setTime(DataTime.TimeFormat.DATE)
        templateDefinition.data.add(DataTest())
        val errors = templateDefinition.validate().getErrors()
        assertEquals(errors.size, 0)
    }

    @Test
    fun failValidation() {
        val errors = templateDefinition.validate().getErrors()
        assertEquals(errors.size, 3)
    }

}