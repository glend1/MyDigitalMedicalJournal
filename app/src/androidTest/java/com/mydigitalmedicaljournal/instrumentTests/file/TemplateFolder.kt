package com.mydigitalmedicaljournal.instrumentTests.file

import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*

class TemplateFolder {
    //TODO should i move context somewhere else?
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var templateList: List<TemplateList.FileList>
    private val folderName = "test_templates"

    private fun makeFile(fileName: String, content: String) {
        val filePath = FileHelper(fileName, context, arrayOf(folderName))
        filePath.write(content)
    }

    @Before
    fun setup() {
        makeFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "{\"data\":[],\"id\":\"793b7045-d572-4110-b4c7-9e1dcfa251f1\",\"name\":\"another test\",\"time\":\"DURATION\"}")
        makeFile("8d319e52-dd89-4ca0-979b-407a1b41c8d5", "{\"data\":[],\"id\":\"8d319e52-dd89-4ca0-979b-407a1b41c8d5\",\"name\":\"last test\",\"time\":\"DATE\"}")
        makeFile("a866ce4a-c509-4d22-9bc3-734ca7c3d649", "{\"data\":[],\"id\":\"a866ce4a-c509-4d22-9bc3-734ca7c3d649\",\"name\":\"this is a test\",\"time\":\"DATETIME\"}")
        templateList = TemplateList.getTemplates(context, arrayOf(folderName))
    }

    @After
    fun teardown() {
        File("${context.filesDir}/$folderName").deleteRecursively()
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