package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.json.FileHelper
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FileHelperTests {
    private lateinit var file: FileHelper
    private lateinit var testText: String

    @Before
    fun setup() {
        file = FileHelper("test", Utils.CONTEXT)
        testText = "test"
        file.write(testText)
    }

    @After
    fun teardown() {
        if (file.exists()) {
            file.delete()
        }
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