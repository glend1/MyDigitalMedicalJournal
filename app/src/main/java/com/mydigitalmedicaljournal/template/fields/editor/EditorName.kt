package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorName(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private val et: EditText = view.findViewById(R.id.nameEditText)
    val error: TextView = view.findViewById(R.id.error)
    private val data = template.getName()

    init {
        et.setText(data.name)
        setSaveListener {
            data.name = et.text.toString()
            val errorRes = data.validate()
            if (errorRes.isEmpty()) {
                error.visibility = View.GONE
                template.setData()
                view.findNavController().navigateUp()
            } else {
                val errorText = view.context.resources.getText(errorRes[DataName.NAME_FIELD]!!)
                Snackbar.make(view, errorText, Snackbar.LENGTH_LONG).show()
                error.text = errorText
                error.visibility = View.VISIBLE
            }
        }
    }
}
