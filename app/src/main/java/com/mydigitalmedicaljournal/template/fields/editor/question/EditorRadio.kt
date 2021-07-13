package com.mydigitalmedicaljournal.template.fields.editor.question

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.question.DataRadio
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class EditorRadio(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data = if (position != null) {
        val newData = template.getData().getData(position) as DataRadio
        newData.setContext(view.context)
        newData
    } else {
        val newData = DataRadio(view.context)
        template.getData().add(newData)
        newData
    }
    private val radioCount: TextView = view.findViewById(R.id.radio_error)
    private val addRadio: Button = view.findViewById(R.id.addRadio)
    private val radioRecycler: RecyclerView = view.findViewById(R.id.radio_recycler)

    init {
        setup(data)
    }

    override fun setData() {
        data.setFormData((radioRecycler.adapter as SortableEditorAdapter).getData())
    }

    override fun setInitialData() {
        errorTextViews[R.id.radio_count] = radioCount
        val viewAdapter = SortableEditorAdapter(data.getFormData(), errorTextViews)
        if (data.getFormData().isEmpty()) {
            viewAdapter.add("")
            viewAdapter.add("")
        }
        val itemDecoration = CustomDivider(view.context)
        radioRecycler.addItemDecoration(itemDecoration)
        radioRecycler.adapter = viewAdapter
        addRadio.setOnClickListener { viewAdapter.add("") }
    }
}