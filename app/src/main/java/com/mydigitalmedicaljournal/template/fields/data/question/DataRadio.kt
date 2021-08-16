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
        getStrRes(R.string.not_found, getStrRes(R.string.radio)),
        getStrRes(R.string.not_found, getStrRes(R.string.radio))
    )
    @Transient private var radioError: String? = getStrRes(R.string.not_enough, getStrRes(R.string.radio))

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
        radioError = if (total >= 2) { null } else { getStrRes(R.string.not_enough, getStrRes(R.string.radio)) }
        errors[R.id.radio_count] = radioError
    }

    private fun validateString(str: String?): String? {
        return if (str.isNullOrBlank()) {
            getStrRes(R.string.not_found, getStrRes(R.string.radio))
        } else if (Regex("^[\\w\\s\\d?]+\$").containsMatchIn(str)) {
            if (str.length <= LENGTH) {
                null
            } else {
                getStrRes(R.string.length, getStrRes(R.string.radio), LENGTH.toString())
            }
        } else {
            getStrRes(R.string.special_symbols, getStrRes(R.string.radio))
        }
    }
}