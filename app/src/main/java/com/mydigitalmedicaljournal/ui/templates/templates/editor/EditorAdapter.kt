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
        return TemplateEnum.layoutList[viewType]!!.createInstance(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenericEditor).setup(holder.itemView, this)
    }

    override fun getItemCount(): Int = localData.data.size + 2

    override fun getItemViewType(position: Int): Int {
        //TODO I assume this isn't null
        return TemplateEnum.values[position].editorLayout
    }

}
