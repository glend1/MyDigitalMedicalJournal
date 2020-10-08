package com.mydigitalmedicaljournal.ui.journal.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelectorAdapter(private val fileList: MutableList<TemplateList.CategoriesTemplate>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = fileList.size

    override fun getItemViewType(position: Int) = fileList[position].type.ordinal

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
        title.text = fileList[position].name.name
        if (holder.itemViewType == 1) {
            item.setOnClickListener {
                //TODO this links to thw wrong fragment, this is placeholder
                val bundle = bundleOf("data" to fileList[position].name.id)
                item.findNavController().navigate(R.id.editorFragment, bundle)
            }
        }
    }
}