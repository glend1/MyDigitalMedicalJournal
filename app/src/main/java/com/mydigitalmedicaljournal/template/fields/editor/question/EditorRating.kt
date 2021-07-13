package com.mydigitalmedicaljournal.template.fields.editor.question

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.question.DataRating
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorRating(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data = if (position != null) {
        val newData = template.getData().getData(position) as DataRating
        newData.setContext(view.context)
        newData
    } else {
        val newData = DataRating(view.context)
        template.getData().add(newData)
        newData
    }
    private val maxField = view.findViewById<EditText>(R.id.maximum)
    private val minField = view.findViewById<EditText>(R.id.minimum)
    private val maxError = view.findViewById<TextView>(R.id.error_max)
    private val minError = view.findViewById<TextView>(R.id.error_min)
    private val minMaxError = view.findViewById<TextView>(R.id.error_min_max)
    //TODO add (optional) labels recycler

    init {
        setup(data)
    }

    override fun setData() {
        data.maxVal = editTextToInt(maxField)
        data.minVal = editTextToInt(minField)
    }

    override fun setInitialData() {
        errorTextViews[R.id.max_error] = maxError
        errorTextViews[R.id.min_error] = minError
        errorTextViews[R.id.min_max_range] = minMaxError
        minField.setText(data.minVal?.toString())
        maxField.setText(data.maxVal?.toString())
    }

    private fun editTextToInt(et: EditText) : Int? {
        return try {
            et.text.toString().toInt()
        } catch (e: Exception) {
            null
        }
    }
}