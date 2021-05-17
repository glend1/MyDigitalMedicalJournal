package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataRating: GenericData() {
    override val type = TemplateEnum.RATING
    override fun listDisplay(context: Context): String = "TempName"
    override fun validate(): MutableMap<Int, Int> {
        return mutableMapOf()
    }
}