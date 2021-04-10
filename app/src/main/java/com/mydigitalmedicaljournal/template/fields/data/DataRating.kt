package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

class DataRating: GenericData() {
    override val type = TemplateEnum.RATING
    override fun validate(): MutableMap<Int, Int> {
        TODO("Not yet implemented")
    }
}