package com.mydigitalmedicaljournal.json


abstract class JsonData {
    abstract val data: Any
    abstract val json: JsonHelper
    abstract val file: FileHelper

    private fun fromJson(): String? {
        return json.convert(data)
    }

    fun save() {
        file.write(fromJson())
    }

    private fun fromFile(): String {
        return file.read()
    }

}