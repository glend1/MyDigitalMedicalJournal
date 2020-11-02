package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.model.Categories
import java.util.*

class DummyCategories(fileName: String = FILENAME) {
    companion object {
        const val FILENAME = "testCategories.json"
    }

    private val categories = Categories(Utils.CONTEXT, fileName)

    init {
        categories.add(Categories.Category(UUID.fromString("c71a34bd-8e95-4394-b0d6-5357c94c2250"), "apple"))
        categories.add(Categories.Category(UUID.fromString("f5c4da32-8b2c-4669-a4b4-235594d49afc"), "cat"))
        categories.add(Categories.Category(UUID.fromString("7838caf6-2746-42ba-9be7-7d7b072ad840"), "dog"))
    }

    fun get(): Categories {
        return categories
    }

    fun delete() {
        categories.delete()
    }
}