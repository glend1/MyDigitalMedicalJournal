package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.template.file.TemplateList
import java.io.File

class DummyTemplates(private val directories: Array<String>) {


    companion object {
        val data = arrayOf(
            arrayOf("793b7045-d572-4110-b4c7-9e1dcfa251f1", "another test"),
            arrayOf("8d319e52-dd89-4ca0-979b-407a1b41c8d5", "last test"),
            arrayOf("a866ce4a-c509-4d22-9bc3-734ca7c3d649", "this is a test")
        )
        const val folderName = "test_templates"
    }

    init {
        for (file in data) {
            DummyTemplateFile(file[0], file[1], directories)
        }
    }

    fun get(): TemplateList {
        return TemplateList(Utils.CONTEXT, directories)
    }

    fun delete() {
        File("${Utils.CONTEXT.filesDir}/${FileHelper.joinDirectories(directories)}").deleteRecursively()
    }
}