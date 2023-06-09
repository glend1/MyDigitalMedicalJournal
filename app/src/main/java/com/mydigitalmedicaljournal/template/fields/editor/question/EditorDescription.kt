package com.mydigitalmedicaljournal.template.fields.editor.question

import android.view.View
import com.mydigitalmedicaljournal.template.fields.data.question.DataDescription
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorDescription(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data: DataDescription = if (position != null) {
        val newData = template.getData().getData(position) as DataDescription
        newData.setContext(view.context)
        newData
    } else {
        val newData = DataDescription(view.context)
        template.getData().add(newData)
        newData
    }

    init {
        setup(data)
    }

    override fun setData() {}
    override fun setInitialData() {}
}