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
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorAdapter(val templateManager: TemplateManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val localData = templateManager.getData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_delete, parent, false)
        return EditorViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val editor = holder as EditorViewHolder
        setTextAndNavigation(editor, position, holder)
        clearRecyclerButtons(editor)
        setEditability(position, editor)
    }

    private fun setTextAndNavigation(editor: EditorViewHolder, position: Int, holder: RecyclerView.ViewHolder) {
        editor.text(templateManager.getData().getData(position).listDisplay())
        editor.itemView.setOnClickListener {
            val bundle = bundleOf("position" to position, "filename" to templateManager.getId())
            holder.itemView.findNavController().navigate(R.id.fieldEditorFragment, bundle)
        }
    }

    private fun clearRecyclerButtons(editor: EditorViewHolder) {
        editor.visibility(R.id.down, GONE)
        editor.visibility(R.id.up, GONE)
        editor.visibility(R.id.delete, GONE)
    }

    private fun setEditability(position: Int, editor: EditorViewHolder) {
        if (position > 1) {
            showDelete(position, editor)
            multipleFields(position, editor)
        }
    }

    private fun isOneField(): Boolean {
        return localData.size() - 1 == 2
    }

    private fun multipleFields(position: Int, editor: EditorViewHolder) {
        if (!isOneField()) {
            firstField(position, editor)
            lastField(position, editor)
            otherFields(position, editor)
        }
    }

    private fun isFirstField(position: Int): Boolean {
        return position == 2
    }

    private fun firstField(position: Int, editor: EditorViewHolder) {
        if (isFirstField(position)) {
            showDown(position, editor)
        }
    }

    private fun isLastField(position: Int): Boolean {
        return localData.size() - 1 == position
    }

    private fun lastField(position: Int, editor: EditorAdapter.EditorViewHolder) {
        if (isLastField(position)) {
            showUp(position, editor)
        }
    }

    private fun otherFields(position: Int, editor: EditorAdapter.EditorViewHolder) {
        if (!isFirstField(position) && !isLastField(position)) {
            showDown(position, editor)
            showUp(position, editor)
        }
    }

    private fun showDown(position: Int, editor: EditorAdapter.EditorViewHolder) {
        editor.visibility(R.id.down, VISIBLE)
        editor.moveDown(moveDown(position))
    }

    private fun showUp(position: Int, editor: EditorViewHolder) {
        editor.visibility(R.id.up, VISIBLE)
        editor.moveUp(moveUp(position))
    }

    private fun showDelete(position: Int, editor: EditorViewHolder) {
        editor.visibility(R.id.delete, VISIBLE)
        editor.delete(delete(position))
    }

    override fun getItemCount(): Int = localData.size()

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
