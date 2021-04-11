package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataDescription: GenericData() {
    override val type = TemplateEnum.DESCRIPTION
    override fun validate(): MutableMap<Int, Int> {
        return mutableMapOf()
    }
}