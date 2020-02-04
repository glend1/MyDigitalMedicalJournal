package com.mydigitalmedicaljournal.model

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.json.JsonHelper


class Categories(context: Context): JsonData() {
    override val fileName = "categories.json"
    override val type = object: TypeToken<MutableList<Category>>(){}.type
    override lateinit var data: MutableList<Category>

    class Category(
        name: String
    ):Entry(name)

    override var json = JsonHelper(type)
    override var file = FileHelper(fileName, context)

    init {
        data = when (file.exists()) {
            true -> {
                json.convert(file.read()) as MutableList<Category>
            }
            false -> {
                ArrayList()
            }
        }
    }

    fun sort() {
        data = data.sortedWith(compareBy { it.name }).toMutableList()
    }
}
