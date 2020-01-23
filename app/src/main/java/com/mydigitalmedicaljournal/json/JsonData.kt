package com.mydigitalmedicaljournal.json

import android.content.Context
import org.json.JSONArray
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance


abstract class JsonData() {
    abstract val data: Any
    abstract val json: JsonHelper
    abstract val file: FileHelper

    fun fromJson(): String? {
        return json.convert(data)
    }

    fun save() {
        file.write(fromJson())
    }

    fun fromFile(): String {
        return file.read()
    }

    fun load(): Any? {
        return json.convert(fromFile())
    }

}