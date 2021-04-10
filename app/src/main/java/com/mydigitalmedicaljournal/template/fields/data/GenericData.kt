package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.template.TemplateEnum

abstract class GenericData {
    abstract val type: TemplateEnum
    abstract fun validate(): MutableMap<Int, Int>
}