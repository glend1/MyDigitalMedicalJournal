package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.template.data.FileList
import java.util.*

class TemplateList(context: Context, pathArray: Array<String> = arrayOf("templates")) {

    private var data = mutableListOf<FileList>()

    init {
        val files = FileHelper.getFileList(context, pathArray)
        for (file in files) {
            val template =
                TemplateManager(
                    context,
                    UUID.fromString(file.name),
                    pathArray
                )
            val fileList =
                FileList(
                    template.getData().name!!,
                    template.getData().getId()
                )
            data.add(fileList)
        }
        sort()
    }

    private fun sort() {
        data = (data.sortedBy { it.name }).toMutableList()
    }

    fun get(): MutableList<FileList> {
        return data
    }

    fun getName(uuid: UUID): FileList? {
        for(template in data) {
            if (template.id == uuid) {
                return template
            }
        }
        return null
    }
}