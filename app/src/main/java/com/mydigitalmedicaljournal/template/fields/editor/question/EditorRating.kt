package com.mydigitalmedicaljournal.template.fields.editor.question

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.question.DataRating
import com.mydigitalmedicaljournal.template.fields.editor.GenericQuestionEditor
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class EditorRating(view: View, template: TemplateManager, position: Int?): GenericQuestionEditor(view, template, position) {
    val data = if (position != null) {
        val newData = template.getData().getData(position) as DataRating
        newData.setContext(view.context)
        newData
    } else {
        val newData = DataRating(view.context)
        template.getData().add(newData)
        newData
    }
    private val maxField = view.findViewById<EditText>(R.id.maximum)
    private val minField = view.findViewById<EditText>(R.id.minimum)
    private val maxError = view.findViewById<TextView>(R.id.error_max)
    private val minError = view.findViewById<TextView>(R.id.error_min)
    private val minMaxError = view.findViewById<TextView>(R.id.error_min_max)
    private val addLabel = view.findViewById<Button>(R.id.addLabel)
    private val ratingRecycler = view.findViewById<RecyclerView>(R.id.rating_label_recycler)

    init {
        setup(data)
    }

    private fun setupRecycler(data: DataRating) {
        val itemDecoration = CustomDivider(view.context)
        ratingRecycler.addItemDecoration(itemDecoration)
        val viewAdapter = OptionalAdapter(data.getFormData(), errorTextViews)
        ratingRecycler.adapter = viewAdapter
        addLabel.setOnClickListener { viewAdapter.add(DataRating.Label()) }
    }

    override fun setData() {
        data.maxVal = editTextToInt(maxField)
        data.minVal = editTextToInt(minField)
        data.setFormData((ratingRecycler.adapter as OptionalAdapter).getData())
    }

    override fun setInitialData() {
        errorTextViews[R.id.max_error] = maxError
        errorTextViews[R.id.min_error] = minError
        errorTextViews[R.id.min_max_range] = minMaxError
        setupRecycler(data)
        minField.setText(data.minVal?.toString())
        maxField.setText(data.maxVal?.toString())
    }

    private fun editTextToInt(et: EditText) : Int? {
        return try {
            et.text.toString().toInt()
        } catch (e: Exception) {
            null
        }
    }

    class OptionalAdapter (data: MutableList<DataRating.Label?>, private val errorTextViews: MutableMap<Int, TextView>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        companion object{
            private const val firstOffset = -1000
            fun getValuePosition(position: Int): Int {
                return firstOffset + position
            }
            private const val secondOffset = -2000
            fun getLabelPosition(position: Int): Int {
                return secondOffset + position
            }
        }
        private val data = mutableListOf<DataRating.Label?>()
        init {
            this.data.addAll(data)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.editor_rating_label_list_item, parent, false)
            return OptionalViewHolder(layout)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            setup(position, holder as OptionalViewHolder)
        }

        fun add(label: DataRating.Label) {
            data.add(label)
            notifyDataSetChanged()
        }

        private fun setup(position: Int, editor: OptionalViewHolder) {
            errorTextViews[getValuePosition(position)] = editor.itemView.findViewById(R.id.error_value)
            errorTextViews[getLabelPosition(position)] = editor.itemView.findViewById(R.id.error_label)
            editor.setChangeListener(position)
            clearRecyclerState(editor)
            editor.delete(delete(position))
        }

        private fun clearRecyclerState(editor: OptionalViewHolder) {
            editor.itemView.findViewById<TextView>(R.id.error_value).visibility = View.GONE
            editor.itemView.findViewById<TextView>(R.id.error_label).visibility = View.GONE
        }

        override fun getItemCount(): Int = data.size

        private fun delete(position: Int) : View.OnClickListener {
            return View.OnClickListener {
                data.removeAt(position)
                notifyDataSetChanged()
            }
        }

        fun getData(): MutableList<DataRating.Label?> {
            return data
        }

        inner class OptionalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            private val label = itemView.findViewById<EditText>(R.id.label_input)
            private val value = itemView.findViewById<EditText>(R.id.value_input)
            private var labelListener: TextWatcher? = null
            private var valueListener: TextWatcher? = null
            fun setChangeListener(position: Int) {
                if (labelListener != null) { label.removeTextChangedListener(labelListener) }
                if (valueListener != null) { value.removeTextChangedListener(valueListener) }
                label.setText(data[position]?.label)
                if (data[position]?.value != null) {
                    value.setText(data[position]?.value.toString())
                }
                labelListener = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        data[position]?.label = s.toString()
                    }
                }
                valueListener = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        try {
                            data[position]?.value = Integer.parseInt(s.toString())
                        } catch (e: Exception) {
                            data[position]?.value = null
                        }
                    }
                }
                label.addTextChangedListener(labelListener)
                value.addTextChangedListener(valueListener)
            }
            fun delete(listener: View.OnClickListener) {
                itemView.findViewById<ImageView>(R.id.delete).setOnClickListener(listener)
            }
        }
    }

}

