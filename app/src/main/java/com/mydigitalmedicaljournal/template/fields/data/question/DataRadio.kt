package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter

class DataRadio(context: Context): GenericQuestionData(context) {

    companion object {
        const val LENGTH = 25
    }

    override val type = TemplateEnum.RADIO
    private var data = mutableListOf<String?>()
    @Transient private var radioErrors = mutableListOf<String?>(
        getStrRes(R.string.NOT_FOUND, getStrRes(R.string.Radio)),
        getStrRes(R.string.NOT_FOUND, getStrRes(R.string.Radio))
    )
    @Transient private var radioError: String? = getStrRes(R.string.NOT_ENOUGH, getStrRes(R.string.Radio))

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

    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {
        var total = 0
        radioErrors.indices.forEach {
            errors[SortableEditorAdapter.getPosition(it)] = radioErrors[it]
            if (errors[SortableEditorAdapter.getPosition(it)] == null) {
                total++
            }
        }
        radioError = if (total >= 2) { null } else { getStrRes(R.string.NOT_ENOUGH, getStrRes(R.string.Radio)) }
        errors[R.id.radio_count] = radioError
    }

    private fun validateString(str: String?): String? {
        return if (str.isNullOrBlank()) {
            getStrRes(R.string.NOT_FOUND, getStrRes(R.string.Radio))
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            if (str.length <= LENGTH) {
                null
            } else {
                getStrRes(R.string.LENGTH, getStrRes(R.string.Radio), LENGTH.toString())
            }
        } else {
            getStrRes(R.string.SPECIAL_SYMBOLS, getStrRes(R.string.Radio))
        }
    }
}