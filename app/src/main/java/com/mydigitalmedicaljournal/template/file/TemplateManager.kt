package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.model.ValidData
import java.util.*

class TemplateManager(private val context: Context, id: UUID = UUID.randomUUID()) {
    //TODO i think the way i need to convert to and from json will be different here, i may have to loop through all of the elements and convert them individually
    private val json by lazy { Gson() }
    private var file: FileHelper = FileHelper(id.toString(), context, arrayOf("templates"))
    private var data = {
        if (file.exists()) {
            val dataString = file.read()
            val type = object : TypeToken<TemplateDefinition>() {}.type!!
            json.fromJson(dataString, type)
        } else {
            TemplateDefinition(id)
        }
    }.invoke()

    fun setData(input: TemplateDefinition) : ValidData {
        val validData = input.validate()
        if (validData.test()) {
            data = input
            save()
        }
        return validData
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
    fun delete() {
        val cat = Categories(context)
        if (cat.deleteTemplate(getId())) {
            file.delete()
        }
    }
    fun fileExists() : Boolean {
        return file.exists()
    }
}