package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories

class DummyCategories(private val fileName: String) {
    companion object {
        const val FILENAME = "testCategories.json"
    }
    private val file = FileHelper(fileName, Utils.CONTEXT)
    val data = arrayOf(
        arrayOf("c71a34bd-8e95-4394-b0d6-5357c94c2250", "apple"),
        arrayOf("f5c4da32-8b2c-4669-a4b4-235594d49afc", "cat"),
        arrayOf("7838caf6-2746-42ba-9be7-7d7b072ad840", "dog")
    )

    fun get(): Categories {
        val cats = mutableListOf<String>()
        for (category in data) {
            cats.add("{\"id\":\"${category[0]}\",\"name\":\"${category[1]}\",\"templates\":[]}")
        }
        file.write("[${cats.joinToString()}]")
        return Categories(Utils.CONTEXT, fileName)
    }

    fun delete() {
        file.delete()
    }
}