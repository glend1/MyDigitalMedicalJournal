package com.mydigitalmedicaljournal.json

import com.google.gson.Gson
import java.lang.reflect.Type

class JsonHelper(private var type: Type) {

    private val json = Gson()
    private var contents: String? = null
    private var data: Any? = null

    fun convert(string: String): Any? {
        data = json.fromJson(string, type)
        return data
    }

    fun convert(obj: Any): String? {
        contents = json.toJson(obj)
        return contents
    }
}

