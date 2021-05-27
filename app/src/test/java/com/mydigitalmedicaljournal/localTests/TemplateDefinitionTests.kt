package com.mydigitalmedicaljournal.localTests

import com.mydigitalmedicaljournal.template.fields.data.question.DataSimple
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
        templateDefinition.getName().name = newName
        assertEquals(templateDefinition.getName().name, newName)
    }

    @Test
    fun testTime() {
        val newTime = DataTime.TimeFormat.DATE
        templateDefinition.getTime().time = newTime
        assertEquals(templateDefinition.getTime().time, newTime)
    }

    @Test
    fun passValidation() {
        templateDefinition.getName().name = "test"
        templateDefinition.getTime().time = DataTime.TimeFormat.DATE
        templateDefinition.add(DataSimple())
        val errors = templateDefinition.validate()
        assertEquals(errors.size, 0)
    }

    @Test
    fun failValidation() {
        val errors = templateDefinition.validate()
        assertEquals(errors.size, 3)
    }

}