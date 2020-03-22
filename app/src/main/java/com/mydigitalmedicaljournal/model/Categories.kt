package com.mydigitalmedicaljournal.model

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.json.JsonGeneric
import java.util.*

class Categories(context: Context): JsonGeneric() {
    override val file = FileHelper("categories.json", context)
    val type = object: TypeToken<MutableList<Category>>(){}.type!!
    var data: MutableList<Category>

    class Category {
        //TODO this seems messy
        val id: UUID
        var name: String
        var templates: MutableList<UUID>

        constructor(name: String) {
            this.name = name
            id = UUID.randomUUID()
            this.templates = mutableListOf()
        }

        constructor(id: UUID, name: String) {
            this.name = name
            this.id = id
            this.templates = mutableListOf()
        }

        constructor(id: UUID, name: String, templates: MutableList<UUID>) {
            this.name = name
            this.id = id
            this.templates = templates
        }
    }

    init {
        data = if (file.exists()) {
            json.fromJson(file.read(), type)
        } else {
            mutableListOf()
        }
    }

    private fun sort() {
        data = data.sortedWith(compareBy { it.name }).toMutableList()
        save()
    }

    private fun save() {
        file.write(json.toJson(data))
    }

    fun size(): Int {
        return data.size
    }

    fun getTemplate(i: Int): MutableList<UUID> {
        return data[i].templates
    }

    fun setTemplate(i: Int, templates: MutableList<UUID>) {
        data[i].templates = templates
        save()
    }

    fun getName(i: Int): String {
        return data[i].name
    }

    fun setName(i: Int, name: String) {
        data[i].name = name
        sort()
    }

    fun add(category: Category) {
        data.add(category)
        sort()
    }

    fun remove(i: Int) {
        data.removeAt(i)
        save()
    }

}