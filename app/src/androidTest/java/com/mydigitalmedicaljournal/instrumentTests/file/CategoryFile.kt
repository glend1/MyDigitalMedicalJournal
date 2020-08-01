package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.Context
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.util.*

class CategoryFile {
    private lateinit var categories: Categories
    private val fileName = "testCategories.json"
    private val file = FileHelper(fileName, Context.CONTEXT)

    @Before
    fun setup() {
        val fileContent = "[{\"id\":\"c71a34bd-8e95-4394-b0d6-5357c94c2250\",\"name\":\"apple\",\"templates\":[]},{\"id\":\"f5c4da32-8b2c-4669-a4b4-235594d49afc\",\"name\":\"cat\",\"templates\":[\"68aa63ff-1e34-49fd-afbd-bffecf95685c\",\"b132f1ab-d50b-4f84-a87e-bfbcadf91281\"]},{\"id\":\"7838caf6-2746-42ba-9be7-7d7b072ad840\",\"name\":\"dog\",\"templates\":[]}]"
        file.write(fileContent)
        categories = Categories(Context.CONTEXT, fileName)
    }

    @After
    fun teardown() {
        file.delete()
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
        val fileContent = "[{\"something\":\"4\"},{\"if\":\"c71a34bd-8e95-4394-b0d6-5357c94c2250\",\"TEST\":\"HELLO\",\"name\":\"apple\",\"templates\":[]},{\"id\":\"f5c4da32-8b2c-4669-a4b4-235594d49afc\",\"name\":\"cat\",\"templates\":[\"68aa63ff-1e34-49fd-afbd-bffecf95685c\",\"b132f1ab-d50b-4f84-a87e-bfbcadf91281\"]},{\"id\":\"7838caf6-2746-42ba-9be7-7d7b072ad840\",\"name\":\"dog\",\"templates\":[]}]"
        file.write(fileContent)
        categories = Categories(Context.CONTEXT, fileName)
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
    fun setTemplate() {
        val templates = mutableListOf<UUID>()
        templates.add(UUID.fromString("68aa63ff-1e34-49fd-afbd-bffecf95685c"))
        templates.add(UUID.fromString("b132f1ab-d50b-4f84-a87e-bfbcadf91281"))
        templates.add(UUID.fromString("4404623c-1696-42f2-b19e-fd4ff43ce544"))
        categories.setTemplate(2, templates)
        assertEquals(categories.getTemplate(2).size, 3)
    }

    @Test
    fun getTemplate() {
        assertEquals(categories.getTemplate(1).size, 2)
    }

    @Test
    fun deleteTemplate() {
        categories.deleteTemplate(UUID.fromString("68aa63ff-1e34-49fd-afbd-bffecf95685c"))
        assertEquals(categories.getTemplate(1).size, 1)
    }
}