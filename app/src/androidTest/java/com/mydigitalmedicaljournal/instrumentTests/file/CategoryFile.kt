package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.model.Categories
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.*

class CategoryFile {
    private lateinit var categories: Categories
    private val dc = DummyCategories()

    @Before
    fun setup() {
        categories = dc.get()
    }

    @After
    fun teardown() {
        dc.delete()
    }

    @Test
    fun fileReadProperly() {
        /*
        in the even gson doesn't know what to do with some data;
            array
                null all fields
            misspelt
                will be nulled
            additional fields
                ignored
         uuids that don't convert properly will be guessed
         */
        assertNotNull(categories.getTemplate(1))
    }

    @Test
    fun testSize() {
        assertEquals(categories.size(), DummyCategories.data.size)
    }

    @Test
    fun testSort() {
        assertEquals(categories.getName(1), DummyCategories.data[1].name)
    }

    @Test
    fun getName() {
        assertEquals(categories.getName(2), DummyCategories.data[2].name)
    }

    @Test
    fun setName() {
        val newName = "doge"
        categories.setName(2, newName)
        assertEquals(categories.getName(2), newName)
    }

    @Test
    fun testAdd() {
        val categoryName = "abc"
        categories.add(Categories.Category(categoryName))
        assertEquals(categories.getName(0), categoryName)
    }

    @Test
    fun testRemove() {
        categories.remove(0)
        assertEquals(categories.getName(0), DummyCategories.data[1].name)
    }

    @Test
    fun setGetDeleteTemplate() {
        val templates = mutableListOf<UUID>()
        val delete = DummyTemplates.data[1][0]
        templates.add(UUID.fromString(DummyTemplates.data[0][0]))
        templates.add(UUID.fromString(delete))
        templates.add(UUID.fromString(DummyTemplates.data[2][0]))
        categories.setTemplate(2, templates)
        assertEquals(categories.getTemplate(2).size, 3)
        categories.deleteTemplate(UUID.fromString(delete))
        assertEquals(categories.getTemplate(2).size, 2)
    }

}