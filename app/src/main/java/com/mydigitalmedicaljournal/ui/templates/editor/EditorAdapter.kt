package com.mydigitalmedicaljournal.ui.templates.editor

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorAdapter(val templateManager: TemplateManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val localData = templateManager.getData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_delete, parent, false)
        return EditorViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val editor = holder as EditorViewHolder
        editor.itemView.setOnClickListener {
            val bundle = bundleOf("position" to position, "filename" to templateManager.getId())
            holder.itemView.findNavController().navigate(R.id.fieldEditorFragment, bundle)
        }
        val downId = R.id.down
        val upId = R.id.up
        when (position) {
            0 -> {
                editor.text("NAME")
                editor.visibility(R.id.delete, GONE)
                editor.visibility(downId, GONE)
                editor.visibility(upId, GONE)
            }
            1 -> {
                editor.text("DATE")
                editor.visibility(R.id.delete, GONE)
                editor.visibility(downId, GONE)
                editor.visibility(upId, GONE)
            }
            else -> {
                editor.text("TEXT")
                editor.delete(delete(position))
                if (localData.size() - 1 == 2) {
                    editor.visibility(downId, GONE)
                    editor.visibility(upId, GONE)
                } else {
                    when (position) {
                        (2) -> {
                            editor.visibility(downId, VISIBLE)
                            editor.visibility(upId, GONE)
                            editor.moveDown(moveDown(position))
                        }
                        (localData.size() - 1) -> {
                            editor.visibility(upId, VISIBLE)
                            editor.visibility(downId, GONE)
                            editor.moveUp(moveUp(position))
                        }
                        else -> {
                            editor.visibility(downId, VISIBLE)
                            editor.visibility(upId, VISIBLE)
                            editor.moveUp(moveUp(position))
                            editor.moveDown(moveDown(position))
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = localData.size()

    fun add(template: GenericData) {
        //TODO validate this
        localData.add(template)
        notifyDataSetChanged()
    }

    private fun delete(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            localData.delete(position)
            notifyDataSetChanged()
        }
    }

    private fun moveUp(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            localData.moveUp(position)
            notifyDataSetChanged()
        }
    }

    private fun moveDown(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            localData.moveDown(position)
            notifyDataSetChanged()
        }
    }

    inner class EditorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun text(text: String) {
            itemView.findViewById<TextView>(R.id.text).text = text
        }
        fun visibility(id: Int, visibility: Int) {
            itemView.findViewById<ImageView>(id).visibility = visibility
        }
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

}
