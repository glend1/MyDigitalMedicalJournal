package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataValue: GenericQuestionData() {
    override val type = TemplateEnum.VALUE

    companion object {
        const val UNIT_LENGTH = R.string.UNIT_LENGTH
        const val UNIT_NOT_FOUND = R.string.UNIT_NOT_FOUND
    }

    private var unitError: Int? = null
    var unit: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                unitError = UNIT_NOT_FOUND
            } else {
                if (value.length <= 10) {
                    field = value
                    unitError = null
                } else {
                    unitError = UNIT_LENGTH
                }
            }
        }

    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        errors[R.id.unit_field] = unitError
    }
}