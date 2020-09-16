package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.template.file.TemplateManager
import java.util.*

class DummyTemplateFile(private val fileName: String, private val name: String, private val directories: Array<String> = arrayOf()) {
    private val filePath = FileHelper(fileName, Utils.CONTEXT, directories)
    //TODO this could be better
    fun get(): TemplateManager {
        filePath.write("{\"data\":[],\"id\":\"${fileName}\",\"name\":\"${name}\",\"time\":\"DURATION\"}")
        return TemplateManager(Utils.CONTEXT, UUID.fromString(fileName), directories)
    }
    fun delete() {
        if (filePath.exists()) {
            filePath.delete()
        }
    }
}