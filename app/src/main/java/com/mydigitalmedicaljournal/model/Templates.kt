package com.mydigitalmedicaljournal.model

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.json.JsonHelper
import java.util.*
import kotlin.collections.ArrayList


class Templates(context: Context): JsonData() {
    override val fileName = "templates.json"
    override val type = object: TypeToken<MutableList<Template>>(){}.type
    override lateinit var data: MutableList<Template>

    class Template : Entry {
        //TODO need to be able to set the TemplateFormat sometimes
        //TODO should i record TemplateFormat as a UUID so that i can use that reference
        lateinit var template: UUID
        //use id as filename
        constructor(name: String) : super(name)
        constructor(id: UUID, name: String) : super(id, name)
    }

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

    fun sort() {
        data = data.sortedWith(compareBy { it.name }).toMutableList()
    }
}
