package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateFolder {
    private lateinit var data: List<TemplateList.FileList>
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
        assertEquals(data.size, 3)
    }

    @Test
    fun getName() {
        assertEquals(data[1].name, "last test")
    }

    @Test
    fun getId() {
        assertEquals(data[1].id, UUID.fromString("8d319e52-dd89-4ca0-979b-407a1b41c8d5"))
    }
}