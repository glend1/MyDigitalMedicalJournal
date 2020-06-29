package com.mydigitalmedicaljournal.template.editor

import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

class EditorTime(itemView: View) : GenericEditor(itemView) {
    companion object { const val ERROR = "DATE_ERROR" }
    private lateinit var rg: RadioGroup
    override fun setup(view: View, adapter: EditorAdapter) {
        rg = view.findViewById(R.id.time_format)
        setEvent(view, adapter)
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
    private fun setEvent(view: View, adapter: EditorAdapter) {
        rg.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            view.findViewById<RadioButton>(i).requestFocus()
            val data = getData(i)
            if (data != null) {
                adapter.localData.time = getData(i)!!
            } else {
                //TODO not sure i need this
                Log.i("TEST", "why null tho? $i")
            }
        }
    }

    private fun setData(localData: TemplateDefinition) {
        if (localData.time != null) { rg.check(localData.time!!.view) }
    }

    private fun getData(selected: Int): DataTime.TimeFormat? {
        return DataTime.TimeFormat.getType(selected)
    }
}