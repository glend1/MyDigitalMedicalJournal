package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataName: GenericData() {

    companion object {
        const val NAME_LENGTH = R.string.NAME_LENGTH
        const val NAME_SYMBOLS = R.string.NAME_SYMBOLS
        const val NAME_NOT_FOUND = R.string.NAME_NOT_FOUND
    }

    override val type = TemplateEnum.NAME
    override fun listDisplay(context: Context): String = "${context.getString(type.listName)} : $name"
    private var nameError: Int? = null
    var name: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                nameError = NAME_NOT_FOUND
            } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(value)) {
                if (value.length <= 25) {
                    field = value
                    nameError = null
                } else {
                    nameError = NAME_LENGTH
                }
            } else {
                nameError = NAME_SYMBOLS
            }
        }

    override fun validate(): MutableMap<Int, Int?> {
        return mutableMapOf(Pair(R.id.name_field, nameError))
    }
}