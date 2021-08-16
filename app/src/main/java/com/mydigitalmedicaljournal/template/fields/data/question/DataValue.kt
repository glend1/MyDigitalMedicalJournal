package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataValue(context: Context): GenericQuestionData(context) {
    companion object {
        const val LENGTH = 10
    }

    override val type = TemplateEnum.VALUE

    @Transient private var unitError: String? = getStrRes(R.string.not_found, getStrRes(R.string.unit))
    var unit: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                unitError = getStrRes(R.string.not_found, getStrRes(R.string.unit))
            } else {
                if (value.length <= LENGTH) {
                    field = value
                    unitError = null
                } else {
                    unitError = getStrRes(R.string.length, getStrRes(R.string.unit), LENGTH.toString())
                }
            }
        }

    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {
        errors[R.id.unit_field] = unitError
    }
}