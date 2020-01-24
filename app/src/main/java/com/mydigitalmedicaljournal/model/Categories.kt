package com.mydigitalmedicaljournal.model

import android.content.Context
import android.util.Log
import com.google.common.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.json.JsonHelper


class Categories(context: Context): JsonData() {
    private val fileName = "categories.json"
    private var type = object: TypeToken<Data<Entry>>(){}.type

    override lateinit var data: Data<Entry>
    override var json = JsonHelper(type)
    override var file = FileHelper(fileName, context)

    init {
        data = when (file.exists()) {
            true -> {
                json.convert(file.read()) as Data<Entry>
            }
            false -> {
                Data()
            }
        }
    }

    class Data<Entry> : ArrayList<Entry>()

    data class Entry(
        var name: String
    )

}
