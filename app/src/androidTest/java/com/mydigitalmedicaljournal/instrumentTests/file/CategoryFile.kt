package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.model.Categories
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.*

class CategoryFile {
    private lateinit var categories: Categories
    private val dc = DummyCategories(DummyCategories.FILENAME)

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
        assertEquals(categories.size(), 3)
    }

    @Test
    fun testSort() {
        assertEquals(categories.getName(1), "cat")
    }

    @Test
    fun getName() {
        assertEquals(categories.getName(2), "dog")
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
        assertEquals(categories.getName(0), "cat")
    }

    @Test
    fun setGetDeleteTemplate() {
        val templates = mutableListOf<UUID>()
        templates.add(UUID.fromString("68aa63ff-1e34-49fd-afbd-bffecf95685c"))
        templates.add(UUID.fromString("b132f1ab-d50b-4f84-a87e-bfbcadf91281"))
        templates.add(UUID.fromString("4404623c-1696-42f2-b19e-fd4ff43ce544"))
        categories.setTemplate(2, templates)
        assertEquals(categories.getTemplate(2).size, 3)
        categories.deleteTemplate(UUID.fromString("68aa63ff-1e34-49fd-afbd-bffecf95685c"))
        assertEquals(categories.getTemplate(2).size, 2)
    }

}