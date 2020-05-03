package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.data.TemplateFormat
import com.mydigitalmedicaljournal.template.data.Time
import java.util.*

class TemplateDefinition {
    //TODO the uuid is set every time?
    var id: UUID = UUID.randomUUID()
    var name: String? = null
    var time: Time.TimeFormat? = null
    var data = mutableListOf<TemplateFormat>()

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
}