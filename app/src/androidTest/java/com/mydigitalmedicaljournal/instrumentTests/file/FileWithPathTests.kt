package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.json.FileHelper
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

class FileWithPathTests {
    private val testText = "test"
    private val path = File("${Utils.CONTEXT.filesDir}/test")
    private val file = FileHelper("test", Utils.CONTEXT, arrayOf("test"))

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