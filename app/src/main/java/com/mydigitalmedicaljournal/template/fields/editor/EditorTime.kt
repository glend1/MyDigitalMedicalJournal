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
    override fun setup(view: View, adapter: EditorAdapter) {
        rg = view.findViewById(R.id.time_format)
        setEvent(adapter)
        setData(adapter.localData)
    }
    override fun errorHandling(view: View, validData: ValidData) {
        val test = validData.getErrors()
        val error = view.findViewById<TextView>(R.id.error)
        if (test.contains(ERROR)) {
            error.visibility = View.VISIBLE
        } else {
            error.visibility = View.GONE
        }
    }
    private fun setEvent(adapter: EditorAdapter) {
        rg.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            radioGroup.findViewById<RadioButton>(i).requestFocus()
            adapter.localData.setTime(getData(i)!!)
        }
    }

    private fun setData(localData: TemplateDefinition) {
        if (localData.getTime() != null) { rg.check(localData.getTime()!!.view) }
    }

    private fun getData(selected: Int): DataTime.TimeFormat? {
        return DataTime.TimeFormat.getType(selected)
    }
}