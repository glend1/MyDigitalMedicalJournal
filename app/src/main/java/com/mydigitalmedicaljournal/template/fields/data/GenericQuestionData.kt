package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R

abstract class GenericQuestionData(context: Context): GenericData(context) {
    companion object {
        const val LENGTH = 80
    }

    override fun listDisplay(): String = "${getStrRes(type.listName)} : $question"
    @Transient private var questionError: String? = getStrRes(R.string.not_found, getStrRes(R.string.question))

    var question: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                questionError = getStrRes(R.string.not_found, getStrRes(R.string.question))
            } else if (Regex("^[\\w\\s\\d]+\$").containsMatchIn(value)) {
                if (value.length <= LENGTH) {
                    field = value
                    questionError = null
                } else {
                    questionError = getStrRes(R.string.length, getStrRes(R.string.question), LENGTH.toString())
                }
            } else {
                questionError = getStrRes(R.string.special_symbols, getStrRes(R.string.question))
            }
        }

    override fun validate(): MutableMap<Int, String?> {
        val errors = mutableMapOf(Pair(R.id.question_field, questionError))
        validateAfterQuestion(errors)
        return errors
    }

    abstract fun validateAfterQuestion(errors: MutableMap<Int, String?>)
}