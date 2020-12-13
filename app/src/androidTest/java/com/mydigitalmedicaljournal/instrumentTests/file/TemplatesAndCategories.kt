package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.*
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class TemplatesAndCategories {
    private lateinit var ct: CategoriesAndTemplatesList
    private var dct = DummyCategoriesAndTemplates()

    @Before
    fun setup() {
        dct.setup()
        ct = CategoriesAndTemplatesList(Utils.CONTEXT, DummyTemplateFile.directory, DummyCategories.FILENAME)
    }

    @After
    fun teardown() {
        dct.delete()
    }

    @Test
    fun getFlat() {
        assertEquals(ct.getFlatList().size, 9)
    }

    @Test
    fun uncategorized() {
        assertEquals(ct.getFlatList()[7].name.name, Utils.CONTEXT.getString(R.string.uncategoriezed))
        assertEquals(ct.getFlatList()[8].name.name, DummyTemplates.data[2][1])
    }

    @Test
    fun noTemplatesForACategory() {
        ct.getFlatList().forEach {
            assertNotEquals(it.name.name, DummyCategories.data[3].name)
        }
    }
}