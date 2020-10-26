package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.template.data.GenericData
import com.mydigitalmedicaljournal.template.editor.EditorName
import com.mydigitalmedicaljournal.template.editor.EditorTime
import java.util.*

class TemplateDefinition(private val id: UUID, var name: String? = null, var time: DataTime.TimeFormat? = null, var data: MutableList<GenericData> = mutableListOf()) {
    //TODO validate as data is added?

    fun getId() : UUID {
        return id
    }

    private fun validName(): Boolean {
        return !name.isNullOrBlank()
    }

    private fun validDate(): Boolean {
        return time != null
    }

    private fun someData(): Boolean {
        //TODO implement this action
        return true
    }

    fun validate(): ValidData {
        val validData = ValidData()
        validData.add(EditorName.ERROR, validName())
        validData.add(EditorTime.ERROR, validDate())
        validData.add("data present", someData())
        return validData
    }

}