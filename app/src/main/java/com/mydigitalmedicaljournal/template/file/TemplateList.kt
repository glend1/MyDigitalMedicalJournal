package com.mydigitalmedicaljournal.template.file

import android.content.Context
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories
import java.util.*

class TemplateList(private val context: Context, pathArray: Array<String> = arrayOf("templates")) {
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
                    template.getName(),
                    template.getId()
                )
            list.add(fileList)
        }
        data = list.sortedBy { it.name }
    }

    fun get(): List<FileList> {
        return data
    }

    private fun getName(uuid: UUID): FileList? {
        for(template in data) {
            if (template.id == uuid) {
                return template
            }
        }
        return null
    }

    data class NestedTemplates(val category: FileList) { val templates = mutableListOf<FileList>() }

    //TODO this is only used in tests, consider removing it
    fun getNestedList(filename: String = "categories.json"): MutableList<NestedTemplates> {
        val categories = Categories(context, filename).get()
        val processedCategoryList = mutableListOf<NestedTemplates>()
        categories.forEach { category ->
            val nt = NestedTemplates(FileList(category.name, category.id))
            category.templates.forEach { template ->
                val t = getName(template)
                if (t != null) {
                    nt.templates.add(t)
                }
            }
            processedCategoryList.add(nt)
        }
        return processedCategoryList
    }

    class CategoriesTemplate(val name: FileList, val type: CategoriesTemplateType)
    enum class CategoriesTemplateType { CATEGORY, TEMPLATE }

    fun getCategoriesAndTemplates(filename: String = "categories.json"): MutableList<CategoriesTemplate> {
        val flatList = mutableListOf<CategoriesTemplate>()
        for (category in getNestedList(filename)) {
            flatList.add(CategoriesTemplate(category.category, CategoriesTemplateType.CATEGORY))
            for (template in category.templates) {
                flatList.add(CategoriesTemplate(template, CategoriesTemplateType.TEMPLATE))
            }
        }
        return flatList
    }
}