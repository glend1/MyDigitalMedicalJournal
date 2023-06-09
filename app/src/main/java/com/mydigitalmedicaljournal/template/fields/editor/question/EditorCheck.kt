package com.mydigitalmedicaljournal.template.fields.editor.question

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.question.DataCheck
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.fields.editor.SortableEditorAdapter
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class EditorCheck(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data = if (position != null) {
        val newData = template.getData().getData(position) as DataCheck
        newData.setContext(view.context)
        newData
    } else {
        val newData = DataCheck(view.context)
        template.getData().add(newData)
        newData
    }
    private val checkCount: TextView = view.findViewById(R.id.check_error)
    private val addCheck: Button = view.findViewById(R.id.addCheck)
    private val checkRecycler: RecyclerView = view.findViewById(R.id.check_recycler)

    init {
        setup(data)
    }

    override fun setData() {
        data.setFormData((checkRecycler.adapter as SortableEditorAdapter).getData())
    }

    override fun setInitialData() {
        errorTextViews[R.id.check_count] = checkCount
        val viewAdapter = SortableEditorAdapter(data.getFormData(), errorTextViews)
        if (data.getFormData().isEmpty()) {
            viewAdapter.add("")
            viewAdapter.add("")
        }
        val itemDecoration = CustomDivider(view.context)
        checkRecycler.addItemDecoration(itemDecoration)
        checkRecycler.adapter = viewAdapter
        addCheck.setOnClickListener { viewAdapter.add("") }
    }
}