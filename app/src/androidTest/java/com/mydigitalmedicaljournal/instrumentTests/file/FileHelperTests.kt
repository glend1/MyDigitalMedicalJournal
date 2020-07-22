package com.mydigitalmedicaljournal.instrumentTests.file

import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.json.FileHelper
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class FileHelperTests {
    //TODO Categories
    //TODO TemplateManager
    //TODO TemplateList
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var file: FileHelper
    private lateinit var testText: String

    @Before
    fun setup() {
        file = FileHelper("test", context)
        testText = "test"
        file.write(testText)
    }

    @After
    fun teardown() {
        file.delete()
    }

    @Test
    fun fileCreation() {
        assertTrue(file.exists())
    }

    @Test
    fun fileContents() {
        val fileText = file.read()
        assertEquals(testText, fileText)
    }

    @Test
    fun fileDeleted() {
        file.delete()
        assertFalse(file.exists())
    }
}