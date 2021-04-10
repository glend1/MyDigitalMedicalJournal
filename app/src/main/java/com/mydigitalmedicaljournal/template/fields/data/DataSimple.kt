package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataSimple: GenericData() {
    override val type = TemplateEnum.SIMPLE
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}