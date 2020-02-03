package com.mydigitalmedicaljournal.model

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.json.JsonHelper


class Templates(context: Context): JsonData() {
    override val fileName = "templates.json"
    override val type = object: TypeToken<MutableList<Template>>(){}.type
    override lateinit var data: MutableList<Template>

    class Template(
        name: String
    ): Entry(name)

    override var json = JsonHelper(type)
    override var file = FileHelper(fileName, context)

    init {
        data = when (file.exists()) {
            true -> {
                json.convert(file.read()) as MutableList<Template>
            }
            false -> {
                ArrayList()
            }
        }
    }
}
