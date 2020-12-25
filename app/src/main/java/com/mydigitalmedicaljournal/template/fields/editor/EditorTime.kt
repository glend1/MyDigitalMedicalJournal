package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

class EditorTime(itemView: View) : GenericEditor(itemView) {
    companion object { const val ERROR = "DATE_ERROR" }
    private lateinit var rg: RadioGroup
    val error: TextView = itemView.findViewById(R.id.error)
    override fun setup(view: View, adapter: EditorAdapter) {
        rg = view.findViewById(R.id.time_format)
        setEvent(adapter)
        val time = adapter.localData.time
        if (TemplateDefinition.validDate(time)) {
            setData(time!!.view)
        }
    }

    override fun errorHandlingOnSave(view: View, validData: ValidData) {
        showError(!validData.getErrors().contains(ERROR))
    }

    private fun showError(bool: Boolean) {
        if (bool) {
            error.visibility = View.GONE
        } else {
            error.visibility = View.VISIBLE
        }
    }

    private fun setEvent(adapter: EditorAdapter) {
        rg.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            radioGroup.findViewById<RadioButton>(i).requestFocus()
            adapter.localData.time = getData(i)!!
        }
    }

    private fun setData(selected: Int) {
        rg.check(selected)
    }

    private fun getData(selected: Int): DataTime.TimeFormat? {
        return DataTime.TimeFormat.getType(selected)
    }
}