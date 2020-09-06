package com.mydigitalmedicaljournal.ui.templates.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.data.GenericData
import com.mydigitalmedicaljournal.template.editor.GenericEditor
import com.mydigitalmedicaljournal.template.file.TemplateDefinition

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var validData: ValidData = ValidData()
    companion object {
        private const val OFFSET = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TemplateEnum.layoutList[viewType]!!.createEditor(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val editor = holder as GenericEditor
        val view = holder.itemView
        editor.setup(view, this)
        editor.errorHandling(view, validData)
    }

    //TODO this is fragile
    override fun getItemCount(): Int = localData.data.size + OFFSET

    override fun getItemViewType(position: Int): Int {
        //TODO this is fragile
        return when (position) {
            0 -> TemplateEnum.NAME.editorLayout
            1 -> TemplateEnum.TIME.editorLayout
            else -> {
                val relativePosition = position - OFFSET
                localData.data[relativePosition].type.editorLayout
            }
       }
    }

    fun validate(vd: ValidData) {
        validData = vd
        notifyDataSetChanged()
    }

    fun add(template: GenericData) {
        localData.data.add(template)
        notifyDataSetChanged()
    }
}
