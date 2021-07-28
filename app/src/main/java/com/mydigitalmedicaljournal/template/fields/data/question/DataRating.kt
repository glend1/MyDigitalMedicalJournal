package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.question.EditorRating

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
        ratingLabelErrors.indices.forEach {
            errors[EditorRating.OptionalAdapter.getLabelPosition(it)] = ratingLabelErrors[it]
        }
        ratingValueErrors.indices.forEach {
            errors[EditorRating.OptionalAdapter.getValuePosition(it)] = ratingValueErrors[it]
        }
    }
    @Transient private var ratingLabelErrors = mutableListOf<String?>()
    @Transient private var ratingValueErrors = mutableListOf<String?>()
    private var labels = mutableListOf<Label?>()
    fun setFormData(input: MutableList<Label?>) {
        labels = mutableListOf()
        ratingLabelErrors = mutableListOf()
        ratingValueErrors = mutableListOf()
        val usedValues = mutableListOf<Int>()
        input.forEach { label ->
            val labelError = validateString(label?.label)
            val valueError = validateValue(label?.value, usedValues)
            if (label?.value != null) { usedValues.add(label.value!!) }
            labels.add(if (labelError == null && valueError == null) {label} else {Label()})
            ratingLabelErrors.add(labelError)
            ratingValueErrors.add(valueError)
        }
        input.sortBy { it?.value }
    }

    data class Label(var value: Int? = null, var label: String? = null)

    private fun validateString(str: String?): String? {
        return if (str.isNullOrBlank()) {
            getStrRes(R.string.NOT_FOUND, getStrRes(R.string.label))
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            val length = 25
            if (str.length <= length) {
                null
            } else {
                getStrRes(R.string.LENGTH, getStrRes(R.string.label), length.toString())
            }
        } else {
            getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.label))
        }
    }


    private fun validateValue(value: Int?, previousValues: List<Int>) : String? {
        return if (maxVal != null && minVal != null && rangeError == null) {
            if (value != null) {
                if (!previousValues.contains(value)) {
                    when {
                        (value > maxVal!!) -> { getStrRes(R.string.too_big, getStrRes(R.string.value)) }
                        (value < minVal!!) -> { getStrRes(R.string.too_small, getStrRes(R.string.value)) }
                        else -> { null }
                    }
                } else {
                    getStrRes(R.string.in_use, getStrRes(R.string.value))
                }
            } else  {
                getStrRes(R.string.NOT_FOUND, getStrRes(R.string.value))
            }
        } else {
            getStrRes(R.string.other_errors)
        }
    }

    fun getFormData(): MutableList<Label?> {
        return labels
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
        return when {
            (value == null) -> getStrRes(R.string.NOT_FOUND, type)
            (value > 1000) -> getStrRes(R.string.too_big, type)
            (value < 0) -> getStrRes(R.string.too_small, type)
            else -> null
        }
    }
}