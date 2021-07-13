package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import java.util.*

class TemplateManager(private val context: Context, private val id: UUID = UUID.randomUUID(), templateFolder: Array<String> = arrayOf("templates")) {
    private val json by lazy { Gson() }
    private var file: FileHelper = FileHelper(id.toString(), context, templateFolder)
    private var data = if (file.exists()) parse(file.read()) else TemplateDefinition(id, mutableListOf(), context)

    private fun parse(string: String): TemplateDefinition {
        val parsed = JsonParser.parseString(string).asJsonObject
        val definitions = mutableListOf<GenericData>()
        for (obj in parsed["data"].asJsonArray) {
            val enum = json.fromJson(obj.asJsonObject["type"], TemplateEnum::class.java)
            val element = json.fromJson(obj, enum.className)
            element.setContext(context)
            definitions.add(element)
        }
        return TemplateDefinition(
            json.fromJson(parsed["id"], UUID::class.java),
            /*json.fromJson(parsed["name"], String::class.java),
            json.fromJson(parsed["time"], DataTime.TimeFormat::class.java),*/
            definitions, context
        )
    }

    fun getId(): UUID {
        return id
    }

    fun getData(): TemplateDefinition {
        return data
    }

    fun getName(): DataName {
        return getData().getName()
    }

    fun getDate(): DataTime {
        return getData().getTime()
    }

    private fun setData(input: TemplateDefinition): Boolean {
        val validData = input.validate()
        if (validData) {
            data = input
            save()
        }
        return validData
    }

    fun setData() = setData(data)

    private fun save() {
        file.write(json.toJson(data))
    }

    fun delete(cat: Categories = Categories(context)) {
        if (cat.deleteTemplate(data.getId())) {
            file.delete()
        }
    }

    fun fileExists() : Boolean {
        return file.exists()
    }

}