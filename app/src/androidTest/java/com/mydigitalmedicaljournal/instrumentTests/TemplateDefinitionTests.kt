package com.mydigitalmedicaljournal.instrumentTests

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
        templateDefinition = TemplateDefinition(templateId, context = Utils.CONTEXT)
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
        assertEquals(templateDefinition.validate(), true)
    }

    @Test
    fun failValidation() {
        //TODO potential case where name/time are null and never checked causing them to validate and allowing a corrupting save
        templateDefinition.getName().name = null
        templateDefinition.getTime().time = null
        assertEquals(templateDefinition.validate(), false)
    }

}