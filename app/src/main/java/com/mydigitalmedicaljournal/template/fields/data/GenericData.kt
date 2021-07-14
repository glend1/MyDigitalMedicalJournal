package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum

abstract class GenericData(@Transient private var context: Context) {
    abstract val type: TemplateEnum
    abstract fun validate(): MutableMap<Int, String?>
    abstract fun listDisplay(): String
    fun errorCount(res: MutableMap<Int, String?>): Int {
        var count = 0
        for ((_, i) in res) {
            if (i != null) {
                count++
            }
        }
        return count
    }

    @JvmName("setCtx")
    fun setContext(context: Context) {
        this.context = context
    }

    protected fun getStrRes(int: Int, vararg strs: String) : String {
        return context.resources.getString(int, *strs)
    }
}