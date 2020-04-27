package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.template.editor.TemplateView
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(TemplateView.getView(viewType)!!.layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewInt = getItemViewType(position)
        val view = TemplateView.getView(viewInt)
        view?.setup(holder.itemView, this)
    }

    override fun getItemCount(): Int = localData.data.size + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TemplateView.NAME.id
            }
            1 -> {
                TemplateView.TIMEFORMAT.id
            }
            else -> {
                localData.data[position - 2].type.id
            }
        }
    }

}
