package com.mydigitalmedicaljournal.template.data

import java.util.*

class TemplatesAsMap(private val completeList: List<FileList>, savedList: MutableList<UUID> = mutableListOf()) {
    val data = mutableMapOf<FileList, Boolean>()
    init {
        completeList.forEach{
            data[it] = savedList.contains(it.id)
        }
    }
    fun getFromPosition(pos: Int): FileList {
        return completeList[pos]
    }
    fun flatten() : MutableList<UUID> {
        val templates = mutableListOf<UUID>()
        data.forEach {
            if (it.value) {
                templates.add(it.key.id)
            }
        }
        return templates
    }
}