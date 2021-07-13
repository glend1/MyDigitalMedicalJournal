package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter

class DataCheck(context: Context): GenericQuestionData(context) {

    override val type = TemplateEnum.CHECK
    private var data = mutableListOf<String?>()
    @Transient private var checkErrors = mutableListOf<String?>()
    @Transient private var checkError: String? = null

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

    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {
        var total = 0
        checkErrors.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = checkErrors[it]
            if (errors[SortableEditorAdapter.getPosition(it)] == null) {
                total++
            }
        }
        checkError = if (total >= 2) { null } else { getStrRes(R.string.NOT_ENOUGH, getStrRes(R.string.Check)) }
        errors[R.id.check_count] = checkError
    }

    private fun validateString(str: String?): String? {
        return if (str.isNullOrBlank()) {
            getStrRes(R.string.NOT_FOUND, getStrRes(R.string.check))
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            val length = 25
            if (str.length <= length) {
                null
            } else {
                getStrRes(R.string.LENGTH, getStrRes(R.string.check), length.toString())
            }
        } else {
            getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.check))
        }
    }
}