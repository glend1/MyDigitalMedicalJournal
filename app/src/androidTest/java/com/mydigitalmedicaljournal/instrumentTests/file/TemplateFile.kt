package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.template.file.TemplateManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class TemplateFile {
    private lateinit var templateManager: TemplateManager
    private val dtf = DummyTemplateFile("793b7045-d572-4110-b4c7-9e1dcfa251f1")

    @Before
    fun setup() {
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
        val categoryName = "testCategories.json"
        val file = FileHelper(categoryName, Utils.CONTEXT)
        val fileContent = "[{\"id\":\"c71a34bd-8e95-4394-b0d6-5357c94c2250\",\"name\":\"apple\",\"templates\":[\"68aa63ff-1e34-49fd-afbd-bffecf95685c\", \"793b7045-d572-4110-b4c7-9e1dcfa251f1\", \"b132f1ab-d50b-4f84-a87e-bfbcadf91281\", \"4404623c-1696-42f2-b19e-fd4ff43ce544\"]}]"
        file.write(fileContent)
        val cat = Categories(Utils.CONTEXT, categoryName)
        templateManager.delete(cat)
        file.delete()
        assertEquals(cat.getTemplate(0).size, 3)
        assertFalse(templateManager.fileExists())
    }

    @Test
    fun setData() {
        val data = templateManager.getData()
        data.name = null
        data.time = null
        val valid = templateManager.setData(data)
        assertEquals(valid.getErrors().size, 2)
    }

    @Test
    fun getData() {
        assertNotNull(templateManager.getData())
    }

    @Test
    fun getId() {
        assertEquals(templateManager.getId(), UUID.fromString("793b7045-d572-4110-b4c7-9e1dcfa251f1"))
    }

    @Test
    fun getName() {
        assertEquals(templateManager.getName(), "another test")
    }
}