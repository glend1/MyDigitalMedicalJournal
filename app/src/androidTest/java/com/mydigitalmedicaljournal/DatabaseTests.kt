package com.mydigitalmedicaljournal

import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.db.DataSource
import com.mydigitalmedicaljournal.db.DbHelper
import junit.framework.TestCase.assertNotNull
import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class DatabaseTests {
    companion object {
        private val context = InstrumentationRegistry.getInstrumentation().targetContext
        lateinit var db: DataSource

        @JvmStatic
        @BeforeClass
        fun setup() {
            db = DataSource(context)
            db.open()
        }

        @JvmStatic
        @AfterClass
        fun teardown() {
            db.close()
        }
    }

    @Test
    fun databaseObject() {
        assertNotNull(db)
    }

    @Test
    fun databaseFile() {
        val path = context.getDatabasePath(DbHelper.DB_FILE_NAME)
        assertTrue(path.isFile)
    }
}