package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataText: GenericData() {
    override val type = TemplateEnum.TEXT
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}