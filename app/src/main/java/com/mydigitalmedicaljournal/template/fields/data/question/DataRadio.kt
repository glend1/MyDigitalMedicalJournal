package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter

class DataRadio: GenericQuestionData() {
    companion object {
        const val OPTION_LENGTH = R.string.OPTION_LENGTH
        const val OPTION_SYMBOLS = R.string.OPTION_SYMBOLS
        const val OPTION_NOT_FOUND = R.string.OPTION_NOT_FOUND
        const val OPION_NOT_ENOUGH = R.string.OPTION_NOT_ENOUGH
    }

    override val type = TemplateEnum.RADIO
    private var data = mutableListOf<String?>()
    private var radioErrors = mutableListOf<Int?>()
    private var radioError: Int? = null

    fun setFormData(input: MutableList<String?>) {
        data = mutableListOf()
        radioErrors = mutableListOf()
        input.forEach {
            val error = validateString(it)
            data.add(if (error == null) {it} else {null})
            radioErrors.add(error)
        }
    }

    fun getFormData(): MutableList<String?> {
        return data
    }

    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        var total = 0
        radioErrors.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = radioErrors[it]
            if (errors[SortableEditorAdapter.getPosition(it)] == null) {
                total++
            }
        }
        radioError = if (total >= 2) { null } else { OPION_NOT_ENOUGH }
        errors[R.id.radio_count] = radioError
    }

    private fun validateString(str: String?): Int? {
        return if (str.isNullOrBlank()) {
            OPTION_NOT_FOUND
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            if (str.length <= 25) {
                null
            } else {
                OPTION_LENGTH
            }
        } else {
            OPTION_SYMBOLS
        }
    }
}