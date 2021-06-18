package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum

abstract class GenericData {
    abstract val type: TemplateEnum
    abstract fun validate(): MutableMap<Int, Int?>
    abstract fun listDisplay(context: Context): String
    fun errorCount(res: MutableMap<Int, Int?>): Int {
        var count = 0
        for ((_, i) in res) {
            if (i != null) {
                count++
            }
        }
        return count
    }
}