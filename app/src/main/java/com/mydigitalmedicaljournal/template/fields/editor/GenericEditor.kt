package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

abstract class GenericEditor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setup(view: View, adapter: EditorAdapter)
    abstract fun errorHandlingOnSave(view: View, validData: ValidData)
    fun delete(listener: View.OnClickListener) {
        itemView.findViewById<ImageView>(R.id.delete).setOnClickListener(listener)
    }
    fun moveUp(listener: View.OnClickListener) {
        itemView.findViewById<ImageView>(R.id.up)?.setOnClickListener(listener)
    }
    fun moveDown(listener: View.OnClickListener) {
        itemView.findViewById<ImageView>(R.id.down)?.setOnClickListener(listener)
    }
}