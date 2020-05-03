package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.mydigitalmedicaljournal.json.FileHelper
import java.util.*
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.file.TemplateDefinition

class TemplateManager {
    //TODO i think the way i need to convert to and from json will be different here, i may have to loop through all of the elements and convert them individually
    constructor() {
        data = TemplateDefinition()
    }
    constructor(name: String, context: Context) {
        file = FileHelper(name, context, arrayOf("templates"))
        data = if (file!!.exists()) {
            val dataString = file!!.read()
            val type = object: TypeToken<TemplateDefinition>(){}.type!!
            json.fromJson(dataString, type)
        } else {
            //TODO i think this else is unnecessary
            TemplateDefinition()
        }
    }

    private val json by lazy { Gson() }
    private var file: FileHelper? = null
    private lateinit var data: TemplateDefinition

    fun setData(input: TemplateDefinition) : ValidData {
        val validData = input.validate()
        if (validData.test()) {
            //data = input
            //save()
        }
        return validData
    }
    fun getData(): TemplateDefinition {
        return data
    }
    private fun save() {
        //TODO This has the potential to fail if file isn't set
        file!!.write(json.toJson(data))
    }
    fun getId(): UUID {
        return data.id
    }
    fun getName(): String {
        return data.name!!
    }
}