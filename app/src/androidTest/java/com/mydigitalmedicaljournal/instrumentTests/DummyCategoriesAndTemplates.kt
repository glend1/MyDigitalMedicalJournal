package com.mydigitalmedicaljournal.instrumentTests

import java.util.*

class DummyCategoriesAndTemplates(categoryFile: String = DummyCategories.FILENAME, templateFolder: Array<String> = DummyTemplateFile.directory) {
    private val dt = DummyTemplates(templateFolder)
    private val dc = DummyCategories(categoryFile)

    companion object{
        val data = arrayOf(
            mutableListOf(UUID.fromString(DummyTemplates.data[1][0])),
            mutableListOf(UUID.fromString(DummyTemplates.data[0][0]), UUID.fromString(DummyTemplates.data[2][0])),
            mutableListOf(UUID.fromString(DummyTemplates.data[1][0]))
        )
    }

    init {
        setup()
    }

    fun setup() {
        dt.get()
        val categoryList = dc.get()
        categoryList.setTemplate(0, data[0])
        categoryList.setTemplate(1, data[1])
        categoryList.setTemplate(2, data[2])
    }

    fun delete() {
        dt.delete()
        dc.delete()
    }
}