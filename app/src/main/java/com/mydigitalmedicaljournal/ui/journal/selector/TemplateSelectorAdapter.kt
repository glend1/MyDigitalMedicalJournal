package com.mydigitalmedicaljournal.ui.journal.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelectorAdapter(private val categoriesAndTemplates: CategoriesAndTemplatesList) : RecyclerView.Adapter<ViewHolder>() {

    private val flatList = categoriesAndTemplates.getFlatList()
    private var filteredList = flatList

    override fun getItemCount() = filteredList.size

    override fun getItemViewType(position: Int) = filteredList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_child, parent, false)
        }
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.itemView
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

    fun filterList(s: CharSequence?) {
        if (s.isNullOrEmpty()) {
            filteredList = flatList
        } else {
            //TODO filter data
            filteredList = mutableListOf()
        }
        notifyDataSetChanged()
    }
}