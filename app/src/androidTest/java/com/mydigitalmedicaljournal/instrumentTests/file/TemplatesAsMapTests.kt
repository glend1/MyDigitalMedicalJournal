package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.data.TemplatesAsMap
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplatesAsMapTests {
    private lateinit var data: List<FileList>
    private val dt = DummyTemplates()
    private lateinit var templateList: TemplateList
    private lateinit var mapTemplate: TemplatesAsMap

    @Before
    fun setup() {
        templateList = dt.get()
        data = templateList.get()
        val dummyData = mutableListOf(UUID.fromString(DummyTemplates.data[1][0]), UUID.fromString(DummyTemplates.data[2][0]))
        mapTemplate = TemplatesAsMap(data, dummyData)
    }

    @After
    fun teardown() {
        dt.delete()
    }

    @Test
    fun flatten() {
        assertEquals(mapTemplate.flatten().size, 2)
    }

    @Test
    fun getFromPosition() {
        assertEquals(mapTemplate.getFromPosition(1), data[1])
    }
}