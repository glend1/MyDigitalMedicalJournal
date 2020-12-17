package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.fields.editor.EditorName
import com.mydigitalmedicaljournal.template.fields.editor.EditorTime
import java.util.*

class TemplateDefinition(private val id: UUID, private var name: String? = null, private var time: DataTime.TimeFormat? = null, var data: MutableList<GenericData> = mutableListOf()) {
    
    fun getId() : UUID {
        return id
    }

    private fun validName(): Boolean {
        return !name.isNullOrBlank()
    }

    //TODO validate data
    fun setName(string: String) {
        name = string
    }

    fun getName(): String? {
        return name
    }

    private fun validDate(): Boolean {
        return time != null
    }

    //TODO validate data
    fun setTime(format : DataTime.TimeFormat) {
        time = format
    }

    fun getTime(): DataTime.TimeFormat? {
        return time
    }

    private fun someData(): Boolean {
        return data.size != 0
    }

    //TODO validate data field

    fun validate(): ValidData {
        val validData = ValidData()
        validData.add(EditorName.ERROR, validName())
        validData.add(EditorTime.ERROR, validDate())
        validData.add("no data", someData())
        return validData
    }

    fun delete(position: Int) {
        data.removeAt(position)
    }

}