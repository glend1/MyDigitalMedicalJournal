package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateFolder {
    private lateinit var templateList: List<TemplateList.FileList>
    private val dt = DummyTemplates(arrayOf(DummyTemplates.folderName))

    @Before
    fun setup() {
        templateList = dt.get()
    }

    @After
    fun teardown() {
        dt.delete()
    }

    @Test
    fun testSize() {
        assertEquals(templateList.size, 3)
    }

    @Test
    fun getName() {
        assertEquals(templateList[1].name, "last test")
    }

    @Test
    fun getId() {
        assertEquals(templateList[1].id, UUID.fromString("8d319e52-dd89-4ca0-979b-407a1b41c8d5"))
    }
}