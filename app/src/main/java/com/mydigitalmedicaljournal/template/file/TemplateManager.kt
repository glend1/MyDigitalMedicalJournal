package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.template.data.GenericData
import java.util.*

class TemplateManager(private val context: Context, id: UUID = UUID.randomUUID(), templateFolder: Array<String> = arrayOf("templates")) {
    private val json by lazy { Gson() }
    private var file: FileHelper = FileHelper(id.toString(), context, templateFolder)
    private var data = {
        if (file.exists()) {
            parse(file.read())
        } else {
            TemplateDefinition(id)
        }
    }.invoke()

    private fun parse(string: String): TemplateDefinition {
        val parsed = JsonParser.parseString(string).asJsonObject
        val definitions = mutableListOf<GenericData>()
        for (obj in parsed["data"].asJsonArray) {
            val enum = json.fromJson(obj.asJsonObject["type"], TemplateEnum::class.java)
            definitions.add(json.fromJson(obj, enum.className))
        }
        return TemplateDefinition(
            json.fromJson(parsed["id"], UUID::class.java),
            json.fromJson(parsed["name"], String::class.java),
            json.fromJson(parsed["time"], DataTime.TimeFormat::class.java),
            definitions
        )
    }

    fun setData(input: TemplateDefinition) : ValidData {
        val validData = input.validate()
        if (validData.getErrors().isEmpty()) {
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
        return getData().getId()
    }
    fun getName(): String {
        return getData().name!!
    }
    fun delete(cat: Categories = Categories(context)) {
        if (cat.deleteTemplate(getId())) {
            file.delete()
        }
    }
    fun fileExists() : Boolean {
        return file.exists()
    }
}