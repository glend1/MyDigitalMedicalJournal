package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataImage: GenericData() {
    override val type = TemplateEnum.IMAGE
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}