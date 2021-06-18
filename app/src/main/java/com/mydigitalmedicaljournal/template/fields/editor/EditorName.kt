package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorName(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val error: TextView = view.findViewById(R.id.error)
    private val et: EditText = view.findViewById(R.id.name)
    private val data = template.getName()

    init {
        errorTextViews[R.id.name_field] = error
        et.setText(data.name)
        setSaveListener {
            data.name = et.text.toString()
            validate(data)
        }
    }
}