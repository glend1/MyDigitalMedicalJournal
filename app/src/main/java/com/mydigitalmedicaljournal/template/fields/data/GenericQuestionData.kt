package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R

abstract class GenericQuestionData(context: Context): GenericData(context) {

    override fun listDisplay(): String = "${getStrRes(type.listName)} : $question"
    @Transient private var questionError: String? = null

    var question: String? = null
        set(value) {
            val length = 80
            if (value.isNullOrBlank()) {
                questionError = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.question))
            } else if (Regex("^[\\w\\s\\d]+\$").containsMatchIn(value)) {
                if (value.length <= length) {
                    field = value
                    questionError = null
                } else {
                    questionError = getStrRes(R.string.LENGTH, getStrRes(R.string.question), length.toString())
                }
            } else {
                questionError = getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.question))
            }
        }

    override fun validate(): MutableMap<Int, String?> {
        val errors = mutableMapOf(Pair(R.id.question_field, questionError))
        validateAfterQuestion(errors)
        return errors
    }

    abstract fun validateAfterQuestion(errors: MutableMap<Int, String?>)
}