package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter

class DataCheck(context: Context): GenericQuestionData(context) {

    companion object {
        const val LENGTH = 25
    }

    override val type = TemplateEnum.CHECK
    private var data = mutableListOf<String?>()
    @Transient private var checkErrors = mutableListOf<String?>(
        getStrRes(R.string.NOT_FOUND, getStrRes(R.string.check)),
        getStrRes(R.string.NOT_FOUND, getStrRes(R.string.check))
    )
    @Transient private var checkError: String? = getStrRes(R.string.NOT_ENOUGH, getStrRes(R.string.Check))

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
            if (str.length <= LENGTH) {
                null
            } else {
                getStrRes(R.string.LENGTH, getStrRes(R.string.check), LENGTH.toString())
            }
        } else {
            getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.check))
        }
    }
}