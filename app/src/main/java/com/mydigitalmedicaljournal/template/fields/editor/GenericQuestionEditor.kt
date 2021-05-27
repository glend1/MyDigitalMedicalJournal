package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.EditText
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.file.TemplateManager

abstract class GenericQuestionEditor(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val et: EditText = view.findViewById(R.id.editText)

    fun setup(data: GenericQuestionData) {
        setInitialQuestion(data)
        setInitialData()
        setSaveListener {
            setQuestion(data)
            setData()
            validate(data)
        }
    }

    private fun setInitialQuestion(data: GenericQuestionData) {
        et.setText(data.question)
    }

    private fun setQuestion(data: GenericQuestionData) {
        data.question = et.text.toString()
    }

    private fun showQuestionError(errorRes: MutableMap<Int, Int>) {
        showError(view.findViewById(R.id.error), errorRes[GenericQuestionData.QUESTION_FIELD]!!)
    }

    override fun showErrors(errorRes: MutableMap<Int, Int>) {
        showQuestionError(errorRes)
        showOtherErrors()
    }

    abstract fun setData()
    abstract fun setInitialData()
    abstract fun showOtherErrors()
}