package com.mydigitalmedicaljournal.model

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.template.file.TemplateList
import java.util.*

class CategoriesAndTemplatesList(context: Context, templateFolder: Array<String> = arrayOf("templates"), categoryFile: String = "categories.json") {
    data class NestedTemplates(val category: FileList) { var templates = mutableListOf<FileList>() }
    private var nestedList: MutableList<NestedTemplates>
    init {
        val categories = Categories(context, categoryFile)
        val templateList = TemplateList(context, templateFolder)
        val processedCategoryList = mutableListOf<NestedTemplates>()
        val mutableTemplates = templateList.get().toMutableList()
        categories.get().forEach { category ->
            val nt = NestedTemplates(FileList(category.name, category.id))
            category.templates.forEach { template ->
                val t = templateList.getName(template)
                if (t != null) {
                    nt.templates.add(t)
                    mutableTemplates.remove(t)
                }
            }
            if (nt.templates.size > 0) {
                processedCategoryList.add(nt)
            }
        }
        if (mutableTemplates.size > 0) {
            val uncategorized = NestedTemplates(FileList(context.getString(R.string.uncategoriezed), UUID.fromString("766c280b-3146-47a8-8fa8-1c8763a75ceb")))
            uncategorized.templates = mutableTemplates
            processedCategoryList.add(uncategorized)
        }
        nestedList = processedCategoryList
    }

    class CategoriesTemplate(val name: FileList, val type: CategoriesTemplateType)
    enum class CategoriesTemplateType { CATEGORY, TEMPLATE }

    fun getFlatList(chars: CharSequence? = null): MutableList<CategoriesTemplate> {
        return flatten(filter(chars))
    }

    private fun flatten(list: MutableList<NestedTemplates>): MutableList<CategoriesTemplate> {
        val flatList = mutableListOf<CategoriesTemplate>()
        for (category in list) {
            flatList.add(CategoriesTemplate(category.category, CategoriesTemplateType.CATEGORY))
            for (template in category.templates) {
                flatList.add(CategoriesTemplate(template, CategoriesTemplateType.TEMPLATE))
            }
        }
        return flatList
    }

    private fun filter(chars: CharSequence?) : MutableList<NestedTemplates> {
        return if (chars.isNullOrEmpty()) {
            nestedList
        } else {
            val filter = mutableListOf<NestedTemplates>()
            nestedList.forEach { cat ->
                if (cat.category.name.contains(chars, true)) {
                    filter.add(cat)
                } else {
                    val category = NestedTemplates(FileList(cat.category.name, cat.category.id))
                    cat.templates.forEach { temp ->
                        if (temp.name.contains(chars, true)) {
                            category.templates.add(FileList(temp.name, temp.id))
                        }
                    }
                    if (category.templates.size > 0) {
                        filter.add(category)
                    }
                }
            }
            filter
        }
    }
}