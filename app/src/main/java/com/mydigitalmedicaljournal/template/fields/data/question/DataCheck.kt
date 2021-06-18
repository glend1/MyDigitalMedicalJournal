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
    val data = mutableListOf<String>()
    var checkError: Int? = null

    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        var total = 0
        data.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = validateString(data[it])
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