package com.mydigitalmedicaljournal.template.fields.editor.question

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.question.DataValue
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorValue(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data = if (position != null) {
        template.getData().getData(position) as DataValue
    } else {
        val newData = DataValue()
        template.getData().add(newData)
        newData
    }
    private val unit: EditText = view.findViewById(R.id.unit_input)
    private val unitError: TextView = view.findViewById(R.id.unit_error)

    init {
        setup(data)
    }

    override fun setData() {
        data.unit = unit.text.toString()
    }

    override fun setInitialData() {
        errorTextViews[R.id.unit_field] = unitError
        unit.setText(data.unit)
    }
}