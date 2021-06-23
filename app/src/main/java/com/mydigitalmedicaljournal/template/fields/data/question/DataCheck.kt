package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter

class DataCheck: GenericQuestionData() {
    companion object {
        const val CHECK_LENGTH = R.string.CHECK_LENGTH
        const val CHECK_SYMBOLS = R.string.CHECK_SYMBOLS
        const val CHECK_NOT_FOUND = R.string.CHECK_NOT_FOUND
        const val CHECK_NOT_ENOUGH = R.string.CHECK_NOT_ENOUGH
    }

    override val type = TemplateEnum.CHECK
    private var data = mutableListOf<String?>()
    private var checkErrors = mutableListOf<Int?>()
    private var checkError: Int? = null

    fun setFormData(input: MutableList<String?>) {
        data = mutableListOf()
        checkErrors = mutableListOf()
        input.forEach {
            val error = validateString(it)
            data.add(if (error == null) {it} else {null})
            checkErrors.add(error)
        }
    }

    fun getFormData(): MutableList<String?> {
        return data
    }

    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        var total = 0
        checkErrors.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = checkErrors[it]
            if (errors[SortableEditorAdapter.getPosition(it)] == null) {
                total++
            }
        }
        checkError = if (total >= 2) { null } else { CHECK_NOT_ENOUGH }
        errors[R.id.check_count] = checkError
    }

    private fun validateString(str: String?): Int? {
        return if (str.isNullOrBlank()) {
            CHECK_NOT_FOUND
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            if (str.length <= 25) {
                null
            } else {
                CHECK_LENGTH
            }
        } else {
            CHECK_SYMBOLS
        }
    }
}