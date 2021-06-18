package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter
import com.mydigitalmedicaljournal.template.fields.editor.question.EditorRadio

class DataRadio: GenericQuestionData() {
    companion object {
        const val OPTION_LENGTH = R.string.OPTION_LENGTH
        const val OPTION_SYMBOLS = R.string.OPTION_SYMBOLS
        const val OPTION_NOT_FOUND = R.string.OPTION_NOT_FOUND
        const val OPION_NOT_ENOUGH = R.string.OPTION_NOT_ENOUGH
    }

    override val type = TemplateEnum.RADIO
    val data = mutableListOf<String>()
    var radioError: Int? = null

    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        var total = 0
        data.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = validateString(data[it])
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