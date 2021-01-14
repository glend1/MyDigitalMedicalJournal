package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.template.fields.data.DataSimple
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
        data.name = name
        data.time = DataTime.TimeFormat.DATE
        data.data.add(DataSimple())
        template.setData()
    }

    fun get(): TemplateManager {
        return template
    }

    fun delete() {
        if (template.fileExists()) {
            template.delete()
        }
    }
}