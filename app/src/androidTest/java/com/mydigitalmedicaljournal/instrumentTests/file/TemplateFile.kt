package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.template.file.TemplateManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TemplateFile {
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile

    @Before
    fun setup() {
        dtf = DummyTemplateFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "test")
        templateManager = dtf.get()
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun fileExists() {
        assertTrue(templateManager.fileExists())
    }

    @Test
    fun delete() {
        dtf.delete()
        assertFalse(templateManager.fileExists())
    }

    @Test
    fun deleteCategory() {
        val dc = DummyCategories()
        val cat = dc.get()
        val templateNum = 1
        cat.setTemplate(templateNum, mutableListOf(templateManager.getData().getId()))
        templateManager.delete(cat)
        val result = cat.getTemplate(templateNum)
        dc.delete()
        assertEquals(result.size, 0)
    }

    @Test
    fun setData() {
        val data = templateManager.getData()
        data.name = null
        data.time = null
        val valid = templateManager.setData()
        assertEquals(valid.getErrors().size, 2)
    }

    @Test
    fun getData() {
        assertNotNull(templateManager.getData())
    }
}