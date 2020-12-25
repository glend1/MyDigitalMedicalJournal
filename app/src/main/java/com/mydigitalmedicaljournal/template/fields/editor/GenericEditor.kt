package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

abstract class GenericEditor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setup(view: View, adapter: EditorAdapter)
    abstract fun errorHandlingOnSave(view: View, validData: ValidData)
}