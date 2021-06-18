package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.file.TemplateManager

abstract class GenericQuestionEditor(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val error: TextView = view.findViewById(R.id.error)
    private val et: EditText = view.findViewById(R.id.editText)

    fun setup(data: GenericQuestionData) {
        errorTextViews[R.id.question_field] = error
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

    abstract fun setData()
    abstract fun setInitialData()
}