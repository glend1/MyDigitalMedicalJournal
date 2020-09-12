package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.template.file.TemplateList.FileList
import java.io.File

class DummyTemplates(private val directories: Array<String>) {

    companion object {
        const val folderName = "test_templates"
    }

    private fun makeFile(fileName: String, content: String) {
        val filePath = FileHelper(fileName, Utils.CONTEXT, directories)
        filePath.write(content)
    }

    fun get(): List<FileList> {
        //TODO this could be better
        makeFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "{\"data\":[],\"id\":\"793b7045-d572-4110-b4c7-9e1dcfa251f1\",\"name\":\"another test\",\"time\":\"DURATION\"}")
        makeFile("8d319e52-dd89-4ca0-979b-407a1b41c8d5", "{\"data\":[],\"id\":\"8d319e52-dd89-4ca0-979b-407a1b41c8d5\",\"name\":\"last test\",\"time\":\"DATE\"}")
        makeFile("a866ce4a-c509-4d22-9bc3-734ca7c3d649", "{\"data\":[],\"id\":\"a866ce4a-c509-4d22-9bc3-734ca7c3d649\",\"name\":\"this is a test\",\"time\":\"DATETIME\"}")
        return TemplateList.getTemplates(Utils.CONTEXT, directories)
    }

    fun delete() {
        File("${Utils.CONTEXT.filesDir}/${FileHelper.joinDirectories(directories)}").deleteRecursively()
    }
}