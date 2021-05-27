package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.EditText
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorName(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val et: EditText = view.findViewById(R.id.nameEditText)
    private val data = template.getName()

    init {
        et.setText(data.name)
        setSaveListener {
            data.name = et.text.toString()
            validate(data)
        }
    }

    override fun showErrors(errorRes: MutableMap<Int, Int>) {
        showError(view.findViewById(R.id.error), errorRes[DataName.NAME_FIELD]!!)
    }


}