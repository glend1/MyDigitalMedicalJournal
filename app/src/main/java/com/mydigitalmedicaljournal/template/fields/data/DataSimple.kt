package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataSimple: GenericData() {
    companion object {
        const val QUESTION_FIELD = R.string.question_field
        const val QUESTION_LENGTH = R.string.QUESTION_LENGTH
        const val QUESTION_SYMBOLS = R.string.QUESTION_SYMBOLS
        const val QUESTION_NOT_FOUND = R.string.QUESTION_NOT_FOUND
    }

    override fun listDisplay(context: Context): String = "${context.getString(type.listName)} : $question"
    private var questionError: Int? = null

    var question: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                questionError = QUESTION_NOT_FOUND
            } else if (Regex("^[\\w\\s\\d]+\$").containsMatchIn(value)) {
                if (value.length <= 80) {
                    field = value
                    questionError = null
                } else {
                    questionError = QUESTION_LENGTH
                }
            } else {
                questionError = QUESTION_SYMBOLS
            }
        }

    override val type = TemplateEnum.SIMPLE
    override fun validate(): MutableMap<Int, Int> {
        val errors = mutableMapOf<Int, Int>()
        if (questionError != null) {
            errors[QUESTION_FIELD] = questionError!!
        }
        return errors
    }
}