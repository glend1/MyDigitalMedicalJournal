package com.mydigitalmedicaljournal.template.fields.editor

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class SortableEditorAdapter(data: MutableList<String?>, private val errorTextViews: MutableMap<Int, TextView>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val offset = -1000
        fun getPosition(position: Int): Int {
            return offset + position
        }
    }

    private val data = mutableListOf<String?>()
    init {
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.editible_list_item_delete, parent, false)
        return SortableViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setup(position, holder as SortableViewHolder)
    }

    fun add(string: String) {
        data.add(string)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<String?> {
        return data
    }

    private fun setup(position: Int, editor: SortableViewHolder) {
        errorTextViews[getPosition(position)] = editor.itemView.findViewById(R.id.error)
        editor.setChangeListener(position)
        clearRecyclerState(editor)
        setEditability(position, editor)
    }

    private fun clearRecyclerState(editor: SortableViewHolder) {
        editor.itemView.findViewById<TextView>(R.id.error).visibility = View.GONE
        editor.visibility(R.id.down, View.GONE)
        editor.visibility(R.id.up, View.GONE)
        editor.visibility(R.id.delete, View.GONE)
    }

    private fun setEditability(position: Int, editor: SortableViewHolder) {
        showDelete(position, editor, if (itemCount <= 2) { View.GONE } else { View.VISIBLE })
        multipleFields(position, editor)
    }

    private fun isOneField(): Boolean {
        return data.size == 1
    }

    private fun multipleFields(position: Int, editor: SortableViewHolder) {
        if (!isOneField()) {
            firstField(position, editor)
            lastField(position, editor)
            otherFields(position, editor)
        }
    }

    private fun isFirstField(position: Int): Boolean {
        return position == 0
    }

    private fun firstField(position: Int, editor: SortableViewHolder) {
        if (isFirstField(position)) {
            showDown(position, editor)
        }
    }

    private fun isLastField(position: Int): Boolean {
        return data.size - 1 == position
    }

    private fun lastField(position: Int, editor: SortableViewHolder) {
        if (isLastField(position)) {
            showUp(position, editor)
        }
    }

    private fun otherFields(position: Int, editor: SortableViewHolder) {
        if (!isFirstField(position) && !isLastField(position)) {
            showDown(position, editor)
            showUp(position, editor)
        }
    }

    private fun showDown(position: Int, editor: SortableViewHolder) {
        editor.visibility(R.id.down, View.VISIBLE)
        editor.moveDown(moveDown(position))
    }

    private fun showUp(position: Int, editor: SortableViewHolder) {
        editor.visibility(R.id.up, View.VISIBLE)
        editor.moveUp(moveUp(position))
    }

    private fun showDelete(position: Int, editor: SortableViewHolder, visibility: Int = View.VISIBLE) {
        editor.visibility(R.id.delete, visibility)
        editor.delete(delete(position))
    }

    override fun getItemCount(): Int = data.size

    private fun delete(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            data.removeAt(position)
            notifyDataSetChanged()
        }
    }

    private fun moveUp(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            val temp = data[position]
            data.removeAt(position)
            data.add(position-1, temp)
            notifyDataSetChanged()
        }
    }

    private fun moveDown(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            val temp = data[position]
            data.removeAt(position)
            data.add(position+1, temp)
            notifyDataSetChanged()
        }
    }

    inner class SortableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val field = itemView.findViewById<EditText>(R.id.text)
        private var listener: TextWatcher? = null
        fun setChangeListener(position: Int) {
            if (listener != null) { field.removeTextChangedListener(listener) }
            field.setText(data[position])
            listener = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    data[position] = s.toString()
                }
            }
            field.addTextChangedListener(listener)
        }
        fun visibility(id: Int, visibility: Int) {
            itemView.findViewById<ImageView>(id).visibility = visibility
        }
        fun delete(listener: View.OnClickListener) {
            itemView.findViewById<ImageView>(R.id.delete).setOnClickListener(listener)
        }
        fun moveUp(listener: View.OnClickListener) {
            itemView.findViewById<ImageView>(R.id.up).setOnClickListener(listener)
        }
        fun moveDown(listener: View.OnClickListener) {
            itemView.findViewById<ImageView>(R.id.down).setOnClickListener(listener)
        }
    }
}