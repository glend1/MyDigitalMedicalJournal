package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataValue: GenericData() {
    override val type = TemplateEnum.VALUE
    override fun validate(): MutableMap<Int, Int> {
        return mutableMapOf()
    }
}