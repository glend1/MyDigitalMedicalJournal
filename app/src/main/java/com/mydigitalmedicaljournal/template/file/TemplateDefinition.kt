package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.data.GenericData
import com.mydigitalmedicaljournal.template.data.DataTime
import java.util.*

class TemplateDefinition {
    //TODO the uuid is set every time?
    var id: UUID = UUID.randomUUID()
    //TODO validate as data is added?
    var name: String? = null
    var time: DataTime.TimeFormat? = null
    var data = mutableListOf<GenericData>()

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
        validData.add("name", validName())
        validData.add("date", validDate())
        validData.add("data present", someData())
        return validData
    }

    fun getEditorLayout(position: Int) : Int {
        //TODO this is fragile
        return when (position) {
            0 -> TemplateEnum.NAME.editorLayout
            1 -> TemplateEnum.TIME.editorLayout
            else -> {
                val relativePosition = position - 2
                data[relativePosition].type.editorLayout
            }
        }
    }

    //TODO this is fragile
    fun getSize(): Int = data.size + 2
}