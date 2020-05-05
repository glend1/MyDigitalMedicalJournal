package com.mydigitalmedicaljournal.template.editor

import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.template.data.DataTime
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

class EditorTime(itemView: View) : GenericEditor(itemView) {
    private lateinit var rg: RadioGroup
    override fun setup(view: View, adapter: EditorAdapter) {
        rg = view.findViewById(R.id.time_format)
        setEvent(view, adapter)
        setData(adapter.localData)
    }
    private fun setEvent(view: View, adapter: EditorAdapter) {
        rg.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            view.findViewById<RadioButton>(i).requestFocus()
            val data = getData(i)
            if (data != null) {
                adapter.localData.time = getData(i)!!
            } else {
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