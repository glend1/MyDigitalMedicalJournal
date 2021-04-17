package com.mydigitalmedicaljournal.ui.journal.selector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import com.mydigitalmedicaljournal.model.CategoriesTemplateType
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelector : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_selector, container, false)
        val viewAdapter = Adapter(CategoriesAndTemplatesList(requireContext()))
        val templateList = root.findViewById<RecyclerView>(R.id.selector_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        val searchView = root.findViewById<EditText>(R.id.search_view)
        val clearButton = root.findViewById<ImageView>(R.id.clear_button)
        searchView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = if (s.isNullOrEmpty()) { View.GONE } else { View.VISIBLE }
                viewAdapter.filterList(s)
            }
        })
        clearButton.setOnClickListener { searchView.setText("") }
        return root
    }

    class Adapter(private var categoriesAndTemplates: CategoriesAndTemplatesList) : RecyclerView.Adapter<ViewHolder>() {
        private var filteredList = categoriesAndTemplates.getFlatList()

        private fun isEmpty() : Boolean {
            return filteredList.isEmpty()
        }

        override fun getItemCount() = if (isEmpty()) { 1 } else { filteredList.size }

        override fun getItemViewType(position: Int) = if (isEmpty()) { CategoriesTemplateType.OTHER.layout } else { filteredList[position].type.layout }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = holder.itemView
            if (isEmpty()) {
                val error = item.findViewById<TextView>(R.id.message)
                if (categoriesAndTemplates.noTemplates()) {
                    error.text = item.resources.getString(R.string.no_templates)
                } else {
                    error.text = item.resources.getString(R.string.no_templates_filtered)
                }
            } else {
                val title = item.findViewById<TextView>(R.id.text)
                title.text = filteredList[position].name.name
                if (holder.itemViewType == 1) {
                    item.setOnClickListener {
                        //TODO this links to the wrong fragment, this is placeholder
                        val bundle = bundleOf("data" to filteredList[position].name.id)
                        item.findNavController().navigate(R.id.editorFragment, bundle)
                    }
                }
            }
        }

        fun filterList(s: CharSequence?) {
            filteredList = categoriesAndTemplates.getFlatList(s)
            notifyDataSetChanged()
        }
    }
}
