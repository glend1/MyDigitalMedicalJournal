package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.mydigitalmedicaljournal.json.FileHelper
import java.util.*

class TemplateList(context: Context, pathArray: Array<String> = arrayOf("templates")) {
    class FileList(val name: String, val id: UUID)
    private var data: List<FileList>

    init {
        val files = FileHelper.getFileList(context, pathArray)
        val list = mutableListOf<FileList>()
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
            list.add(fileList)
        }
        data = list.sortedBy { it.name }
    }

    fun get(): List<FileList> {
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