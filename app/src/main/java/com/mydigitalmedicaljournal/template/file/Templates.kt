package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.json.JsonHelper
import java.util.*
import kotlin.collections.ArrayList

//TODO this class will be replaced by template, it will generate a list of templates
class OldTemplates(context: Context): JsonData() {
    override val fileName = "templates.json"
    override val type = object: TypeToken<MutableList<Template>>(){}.type!!
    override lateinit var data: MutableList<Template>

    class Template : Entry {
        constructor(name: String) : super(name)
        constructor(id: UUID, name: String) : super(id, name)
        //TODO use id as filename
    }

    override var json = JsonHelper(type)
    override var file = FileHelper(fileName, context)

    init {
        data = when (file.exists()) {
            true -> {
                json.convert(file.read()!!) as MutableList<Template>
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
