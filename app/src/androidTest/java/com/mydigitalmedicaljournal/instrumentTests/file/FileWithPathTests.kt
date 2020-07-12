package com.mydigitalmedicaljournal.instrumentTests.file

import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.json.FileHelper
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

class FileWithPathTests {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val testText = "test"
    private val path = File("${context.filesDir}/test")
    private val file = FileHelper("test", context, arrayOf("test"))

    @Before
    fun setup() {
        file.write(testText)
    }

    @Test
    fun pathCreation() {
        assertTrue(path.exists())
        file.delete()
    }

    @Test
    fun pathDeletion() {
        file.delete()
        assertFalse(path.exists())
    }
}