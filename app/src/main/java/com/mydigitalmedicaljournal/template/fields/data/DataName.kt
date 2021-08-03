package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataName(context: Context): GenericData(context) {
    companion object {
        const val LENGTH = 25
    }

    override val type = TemplateEnum.NAME
    override fun listDisplay(): String = "${getStrRes(type.listName)} : $name"
    @Transient private var nameError: String? = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.name))
    var name: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                nameError = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.name))
            } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(value)) {
                if (value.length <= LENGTH) {
                    field = value
                    nameError = null
                } else {
                    nameError = getStrRes(R.string.LENGTH, getStrRes(R.string.name), LENGTH.toString())
                }
            } else {
                nameError = getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.name))
            }
        }

    override fun validate(): MutableMap<Int, String?> {
        return mutableMapOf(Pair(R.id.name_field, nameError))
    }
}