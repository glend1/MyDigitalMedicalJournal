package com.mydigitalmedicaljournal.ui.templates.editor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.fields.editor.GenericEditor
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

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
        return if (viewType != R.layout.editor_no_data) {
            TemplateEnum.layoutList[viewType]!!.createEditor(layout)
        } else {
            ViewHolder(layout)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != R.layout.editor_no_data) {
            val editor = holder as GenericEditor
            val view = holder.itemView
            editor.setup(view, this)
            editor.errorHandlingOnSave(view, validData)
            editor.delete(delete(position))
        }
    }

    override fun getItemCount(): Int {
        var result = positionToView(localData.data.size)
        if (validData.getErrors().contains("no data")) {
            result++
        }
        return result
    }

    override fun getItemViewType(position: Int): Int {
        //TODO this is fragile
        return when (position) {
            0 -> TemplateEnum.NAME.editorLayout
            1 -> TemplateEnum.TIME.editorLayout
            else -> {
                val relativePosition = viewToPosition(position)
                if (localData.data.size != 0) {
                    localData.data[relativePosition].type.editorLayout
                } else {
                    R.layout.editor_no_data
                }
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
