package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataCheck: GenericData() {
    override val type = TemplateEnum.CHECK
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}