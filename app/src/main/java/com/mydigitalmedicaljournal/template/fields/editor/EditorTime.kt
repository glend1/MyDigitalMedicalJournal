package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.RadioGroup
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorTime(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    private var rg: RadioGroup = view.findViewById(R.id.time_format)
    private val data = template.getDate()
    init {
        selectRadio(data.time)
        setSaveListener {
            setData()
            validate(data)
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

    override fun showErrors(errorRes: MutableMap<Int, Int>) {
        showError(view.findViewById(R.id.error), errorRes[DataTime.TIME_FIELD]!!)
    }

}