package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.mydigitalmedicaljournal.json.FileHelper
import java.util.*
import com.google.gson.reflect.TypeToken

class TemplateManager(name: String, context: Context) {
    //TODO finish this class
    //TODO i think the way i need to convert to and from json will be different here, i may have to loop through all of the elements and convert them individually
    private val json by lazy { Gson() }
    private val file = FileHelper(name, context, arrayOf("templates"))
    private lateinit var data: TemplateDefinition
    init {
        val dataString = file.read()
        data = if (dataString == null) {
            TemplateDefinition()
        } else {
            val type = object: TypeToken<TemplateDefinition>(){}.type!!
            json.fromJson(dataString, type)
        }
    }
    fun setData(input: TemplateDefinition) {
        data = input
        save()
    }
    fun getData(): TemplateDefinition {
        return data
    }
    private fun save() {
        file.write(json.toJson(data))
    }
    fun getId(): UUID {
        return data.id
    }
    fun getName(): String {
        return data.name!!
    }
}