package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.mydigitalmedicaljournal.json.FileHelper
import java.util.*

class TemplateList {
    companion object {
        class FileList(val name: String, val id: UUID)
        fun getTemplates(context: Context, pathArray: Array<String>): MutableList<FileList> { //Array
            val files = FileHelper.getFileList(context, pathArray)
            val list = mutableListOf<FileList>()
            for (file in files) {
                val template = TemplateManager(file.name, context)
                val fileList = FileList(template.getName(), template.getId())
                //TODO sort input
                list.add(fileList)
            }
            return list
        }
    }
}