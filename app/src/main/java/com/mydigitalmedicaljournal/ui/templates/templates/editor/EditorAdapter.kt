package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.editor.GenericEditor
import com.mydigitalmedicaljournal.template.file.TemplateDefinition

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var validData: ValidData = ValidData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TemplateEnum.layoutList[viewType]!!.createEditor(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val editor = holder as GenericEditor
        val view = holder.itemView
        editor.setup(view, this)
        //TODO this needs support for the same type of errors
        editor.errorHandling(view, validData)
    }

    override fun getItemCount(): Int = localData.getSize()

    override fun getItemViewType(position: Int): Int = localData.getEditorLayout(position)

    fun validate(vd: ValidData) {
        //TODO this could bug if you try to add more fields
        validData = vd
        notifyDataSetChanged()
    }
}
