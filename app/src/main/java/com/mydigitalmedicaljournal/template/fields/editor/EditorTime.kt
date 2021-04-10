package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorTime(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private var rg: RadioGroup = view.findViewById(R.id.time_format)
    val error: TextView = view.findViewById(R.id.error)
    private val data = template.getDate()
    init {
        selectRadio(data.time)
        setSaveListener {
            setData()
            val errorRes = data.validate()
            if (errorRes.isEmpty()) {
                error.visibility = View.GONE
                template.setData()
                view.findNavController().navigateUp()
            } else {
                val errorText = view.context.resources.getText(errorRes[DataTime.TIME_FIELD]!!)
                Snackbar.make(view, errorText, Snackbar.LENGTH_LONG).show()
                error.text = errorText
                error.visibility = View.VISIBLE
            }
        }
    }

    private fun selectRadio(selected: DataTime.TimeFormat?) {
        if (selected != null) {
            rg.check(selected.view)
        }
    }

    private fun getRadio(): DataTime.TimeFormat? {
        return DataTime.TimeFormat.getType(rg.checkedRadioButtonId)
    }

    private fun setData() {
        data.time = getRadio()
    }

}