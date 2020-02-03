package com.mydigitalmedicaljournal.json

import java.lang.reflect.Type

abstract class JsonData {
    protected abstract val fileName: String
    protected abstract val type: Type
    abstract val data: MutableList<out Entry>

    abstract class Entry(open var name: String)

    abstract val json: JsonHelper
    abstract val file: FileHelper

    private fun fromJson(): String? {
        return json.convert(data)
    }

    fun save() {
        file.write(fromJson())
    }

    protected fun fromFile(): String {
        return file.read()
    }
}