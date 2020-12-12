package com.mydigitalmedicaljournal.model

import android.content.Context
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.file.TemplateList

class CategoriesAndTemplatesList(context: Context, templateFolder: Array<String> = arrayOf("templates"), categoryFile: String = "categories.json") {
    data class NestedTemplates(val category: FileList) { var templates = mutableListOf<FileList>() }
    private var nestedList: MutableList<NestedTemplates>
    init {
        val categories = Categories(context, categoryFile)
        val templateList = TemplateList(context, templateFolder)
        val processedCategoryList = mutableListOf<NestedTemplates>()
        categories.get().forEach { category ->
            val nt = NestedTemplates(FileList(category.name, category.id))
            category.templates.forEach { template ->
                val t = templateList.getName(template)
                if (t != null) {
                    nt.templates.add(t)
                }
            }
            //TODO doesn't show uncategoried templates
            processedCategoryList.add(nt)
        }
        nestedList = processedCategoryList
    }

    class CategoriesTemplate(val name: TemplateList.FileList, val type: CategoriesTemplateType)
    enum class CategoriesTemplateType { CATEGORY, TEMPLATE }

    fun getFlatList(filter : String? = null): MutableList<CategoriesTemplate> {
        val flatList = mutableListOf<CategoriesTemplate>()
        for (category in nestedList) {
            flatList.add(CategoriesTemplate(category.category, CategoriesTemplateType.CATEGORY))
            for (template in category.templates) {
                flatList.add(CategoriesTemplate(template, CategoriesTemplateType.TEMPLATE))
            }
        }
        return flatList
    }

}