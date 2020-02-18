package com.mydigitalmedicaljournal.json

import java.lang.reflect.Type
import java.util.*

abstract class JsonData {
    protected abstract val fileName: String
    protected abstract val type: Type
    abstract val data: MutableList<out Entry>
    //TODO need good way to sort this

    abstract class Entry {
        var id: UUID
        var name: String

        constructor(name: String) {
            this.name = name
            id = UUID.randomUUID()
        }
        constructor(id: UUID, name: String) {
            this.name = name
            this.id = id
        }
    }

    abstract val json: JsonHelper
    abstract val file: FileHelper

    private fun fromJson(): String? {
        return json.convert(data)
    }

    fun save() {
        file.write(fromJson())
    }

}