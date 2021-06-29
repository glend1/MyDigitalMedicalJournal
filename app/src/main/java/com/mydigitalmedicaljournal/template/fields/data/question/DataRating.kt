package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataRating: GenericQuestionData() {
    companion object {
        const val MIN_RATING_SIZE = R.string.MIN_RATING_SIZE
        const val MAX_RATING_SIZE = R.string.MAX_RATING_SIZE
        const val MIN_RATING_NOT_FOUND = R.string.MIN_RATING_NOT_FOUND
        const val MAX_RATING_NOT_FOUND = R.string.MAX_RATING_NOT_FOUND
        const val MIN_MAX_NOT_VALID = R.string.MIN_MAX_NOT_VALID
    }

    override val type = TemplateEnum.RATING
    private var minError: Int? = null
    var minVal: Int? = null
        set(value) {
            minError = validateNumber(value)
            if (minError == null) { field = value }
            validateRange()
        }
    private var maxError: Int? = null
    var maxVal: Int? = null
        set(value) {
            maxError = validateNumber(value)
            if (maxError == null) { field = value }
            validateRange()
        }
    private var rangeError: Int? = null
    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        errors[R.id.max_error] = maxError
        errors[R.id.min_error] = minError
        errors[R.id.min_max_range] = rangeError
    }

    private fun validateRange() {
        rangeError = null
        if (minVal != null && maxVal != null) {
            if (minVal!! >= maxVal!!) {
                rangeError = MIN_MAX_NOT_VALID
            }
        }
    }

    private fun validateNumber(value: Int?): Int? {
        //TODDO this needs to be worked after i have refactoed strings.xml
        if (value == null) {
            return MIN_RATING_NOT_FOUND
        } else {
            if (value > 1000 || value < 0) {
                return MIN_RATING_SIZE
            }
        }
        return null
    }
}