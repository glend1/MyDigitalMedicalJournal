package com.mydigitalmedicaljournal.json

//TODO Delete this?
abstract class JsonGeneric {



    //JSON

    /*
    private var contents: String? = null
    private var data: Any? = null

    fun convert(string: String): ArrayList<JsonData.Entry> {
        data = json.fromJson(string, type)
        return data as ArrayList<JsonData.Entry>
    }

    fun convert(obj: Any): String? {
        contents = json.toJson(obj)
        return contents
    }

    //DATA

    protected abstract val fileName: String
    protected abstract val type: Type
    abstract val data: MutableList<out Entry>

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

     */

}