package com.mydigitalmedicaljournal.instrumentTests.file

import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.model.Categories
import org.junit.Before
import org.junit.Test

class CategoryFile {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var categories: Categories

    @Before
    fun setup() {
        //TODO what if json is malformed?
        categories = Categories(context, "testCategories.json")
    }

    @Test
    fun validData() {
        categories.add(Categories.Category("test"))
    }
}