package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataDescription
import com.mydigitalmedicaljournal.template.fields.data.DataSimple
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorDescription(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val et: EditText = view.findViewById(R.id.editText)
    private val error: TextView = view.findViewById(R.id.error)
    private val data = if (position != null) {
        template.getData().getData(position) as DataDescription
    } else {
        val newData = DataDescription()
        template.getData().add(newData)
        newData
    }

    init {
        et.setText(data.question)
        setSaveListener {
            data.question = et.text.toString()
            val errorRes = data.validate()
            if (errorRes.isEmpty()) {
                error.visibility = View.GONE
                template.setData()
                view.findNavController().navigateUp()
            } else {
                val errorText = view.context.resources.getText(errorRes[DataSimple.QUESTION_FIELD]!!)
                Snackbar.make(view, errorText, Snackbar.LENGTH_LONG).show()
                error.text = errorText
                error.visibility = View.VISIBLE
            }
        }
    }
}