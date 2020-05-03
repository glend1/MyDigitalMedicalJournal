package com.mydigitalmedicaljournal.template.editor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

abstract class GenericEditor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setup(view: View, adapter: EditorAdapter)
    //TODO needs an error message
    //abstract fun error()
}