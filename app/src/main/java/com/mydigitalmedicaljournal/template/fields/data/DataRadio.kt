package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataRadio: GenericData() {
    override val type = TemplateEnum.RADIO
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}