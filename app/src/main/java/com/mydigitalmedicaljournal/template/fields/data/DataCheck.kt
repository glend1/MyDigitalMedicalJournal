package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataCheck: GenericData() {
    override val type = TemplateEnum.CHECK
    override fun listDisplay(context: Context): String = "TempName"
    override fun validate(): MutableMap<Int, Int> {
        return mutableMapOf()
    }
}