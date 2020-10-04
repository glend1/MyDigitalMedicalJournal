package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateFolder {
    private lateinit var data: List<TemplateList.FileList>
    private val dt = DummyTemplates(arrayOf(DummyTemplates.folderName))
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

    @Test
    fun getNested() {
        val dc = DummyCategories(DummyCategories.FILENAME)
        val categories = dc.get()
        categories.setTemplate(0, mutableListOf(data[1].id))
        categories.setTemplate(1, mutableListOf(data[0].id, data[2].id))
        categories.setTemplate(2, mutableListOf(data[1].id))
        val nl = dt.get().getNestedList(DummyCategories.FILENAME)
        assertEquals(nl[1].templates.size, 2)
    }
}