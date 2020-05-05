package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.editor.GenericEditor
import com.mydigitalmedicaljournal.template.file.TemplateDefinition

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TemplateEnum.layoutList[viewType]!!.createEditor(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenericEditor).setup(holder.itemView, this)
    }

    override fun getItemCount(): Int = localData.getSize()

    override fun getItemViewType(position: Int): Int = localData.getEditorLayout(position)

}
