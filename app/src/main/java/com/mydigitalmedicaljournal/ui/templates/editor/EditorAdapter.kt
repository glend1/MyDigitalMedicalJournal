package com.mydigitalmedicaljournal.ui.templates.editor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.fields.editor.GenericEditor
import com.mydigitalmedicaljournal.template.file.TemplateDefinition

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var validData: ValidData = ValidData()
    companion object {
        //TODO this is fragile
        private const val OFFSET = 2
        private fun positionToView(position: Int) : Int {
            return position + OFFSET
        }

        private fun viewToPosition(position: Int) : Int {
            return position - OFFSET
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return TemplateEnum.layoutList[viewType]!!.createEditor(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val editor = holder as GenericEditor
        val view = holder.itemView
        editor.setup(view, this)
        editor.errorHandlingOnSave(view, validData)
        editor.delete(delete(position))
    }

    override fun getItemCount(): Int = positionToView(localData.data.size)

    override fun getItemViewType(position: Int): Int {
        //TODO this is fragile
        return when (position) {
            0 -> TemplateEnum.NAME.editorLayout
            1 -> TemplateEnum.TIME.editorLayout
            else -> {
                val relativePosition = viewToPosition(position)
                localData.data[relativePosition].type.editorLayout
            }
        }
    }

    fun validate(vd: ValidData) {
        validData = vd
        notifyDataSetChanged()
    }

    fun add(template: GenericData) {
        validData = ValidData()
        localData.data.add(template)
        notifyDataSetChanged()
    }

    fun delete(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            localData.delete(viewToPosition(position))
            notifyDataSetChanged()
        }
    }
}
