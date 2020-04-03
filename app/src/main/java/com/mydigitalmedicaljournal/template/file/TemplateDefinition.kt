package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.template.file.properties.TemplateFormat
import com.mydigitalmedicaljournal.template.file.properties.Time
import java.util.*

class TemplateDefinition {
    var id: UUID = UUID.randomUUID()
    var name: String? = null
    var time: Time.TimeFormat? = null
    var data = mutableListOf<TemplateFormat>()
}