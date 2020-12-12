package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateFolder {
    private lateinit var data: List<FileList>
    private val dt = DummyTemplates()
    private lateinit var templateList: TemplateList

    @Before
    fun setup() {
        templateList = dt.get()
        data = templateList.get()
    }

    @After
    fun teardown() {
        dt.delete()
    }

    @Test
    fun testSize() {
        assertEquals(data.size, DummyTemplates.data.size)
    }

    @Test
    fun getFileList() {
        assertEquals(templateList.getName(UUID.fromString(DummyTemplates.data[1][0])), data[1])
    }

    @Test
    fun sorting() {
        assertEquals(data[3].name, DummyTemplates.data[2][1])
    }

    @Test
    fun getName() {
        assertEquals(data[1].name, DummyTemplates.data[1][1])
    }

    @Test
    fun getId() {
        assertEquals(data[1].id, UUID.fromString(DummyTemplates.data[1][0]))
    }
}