package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.mydigitalmedicaljournal.json.FileHelper

class TemplateManager(name: String, context: Context) {
    //TODO finish this class
    //TODO i think the way i need to convert to and from json will be different here, i may have to loop through all of the elements and convert them individually
    private val json by lazy { Gson() }
    private val file = FileHelper(name, context, arrayOf("templates"))
    private lateinit var data: TemplateDefinition
    fun setData(input: TemplateDefinition) {
        data = input
        save()
    }
    private fun save() {
        file.write(json.toJson(data))
    }
}