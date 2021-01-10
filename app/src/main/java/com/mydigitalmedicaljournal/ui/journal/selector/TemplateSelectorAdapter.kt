package com.mydigitalmedicaljournal.ui.journal.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import com.mydigitalmedicaljournal.model.CategoriesTemplateType
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelectorAdapter(private var categoriesAndTemplates: CategoriesAndTemplatesList) : RecyclerView.Adapter<ViewHolder>() {
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