package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.template.fields.data.question.DataSimple
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateManager
import java.util.*

class DummyTemplateFile(fileName: String, name: String, dir : Array<String> = directory) {
    companion object {
        val directory: Array<String> = arrayOf("test_templates")
    }
    private val template = TemplateManager(Utils.CONTEXT, UUID.fromString(fileName), dir)

    init {
        val data = template.getData()
        data.getName().name = name
        data.getTime().time = DataTime.TimeFormat.DATE
        template.validateAndSave()
    }

    fun get(): TemplateManager {
        return template
    }

    fun delete() {
        if (template.fileExists()) {
            template.delete()
        }
    }

    fun addSimple(question: String): DataSimple {
        val data = DataSimple(Utils.CONTEXT)
        data.question = question
        template.getData().add(data)
        template.validateAndSave()
        return data
    }
}