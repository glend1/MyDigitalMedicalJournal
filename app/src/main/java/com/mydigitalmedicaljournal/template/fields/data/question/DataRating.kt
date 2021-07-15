package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataRating(context: Context): GenericQuestionData(context) {

    override val type = TemplateEnum.RATING
    @Transient private var minError: String? = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.minimum))
    var minVal: Int? = null
        set(value) {
            minError = validateNumber(value, getStrRes(R.string.minimum))
            if (minError == null) { field = value }
            validateRange()
        }
    @Transient private var maxError: String? = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.maximum))
    var maxVal: Int? = null
        set(value) {
            maxError = validateNumber(value, getStrRes(R.string.maximum))
            if (maxError == null) { field = value }
            validateRange()
        }
    @Transient private var rangeError: String? = getStrRes(R.string.MIN_MAX_NOT_VALID)
    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {
        errors[R.id.max_error] = maxError
        errors[R.id.min_error] = minError
        errors[R.id.min_max_range] = rangeError
    }

    private fun validateRange() {
        rangeError = null
        if (minVal != null && maxVal != null) {
            if (minVal!! >= maxVal!!) {
                rangeError = getStrRes(R.string.MIN_MAX_NOT_VALID)
            }
        }
    }

    private fun validateNumber(value: Int?, type: String): String? {
        if (value == null) {
            return getStrRes(R.string.NOT_FOUND, type)
        } else {
            if (value > 1000 || value < 0) {
                return getStrRes(R.string.RATING_SIZE, type)
            }
        }
        return null
    }
}