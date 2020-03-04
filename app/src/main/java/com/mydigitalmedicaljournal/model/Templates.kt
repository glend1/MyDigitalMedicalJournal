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
        //TODO use id as filename
        var template = UUID.randomUUID()
        constructor(name: String) : super(name)
        constructor(id: UUID, name: String) : super(id, name)
        constructor(id: UUID, name: String, template: UUID) : this(id, name) {
            this.template = template
        }
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
