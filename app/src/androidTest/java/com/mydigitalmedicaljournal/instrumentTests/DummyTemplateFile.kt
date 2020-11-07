package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.template.data.DataTest
import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateManager
import java.util.*

class DummyTemplateFile(fileName: String, name: String, dir : Array<String> = directory) {
    companion object {
        val directory: Array<String> = arrayOf("test_templates")
    }
    private val template = TemplateManager(Utils.CONTEXT, UUID.fromString(fileName), dir)

    init {
        //TODO i don't like how i have to create all this data
        val data = template.getData()
        data.name = name
        data.time = DataTime.TimeFormat.DATE
        data.data.add(DataTest())
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