package com.mydigitalmedicaljournal.ui.templates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateListAdapter(private var fileList: List<FileList>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount() = fileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.itemView
        val title = item.findViewById<TextView>(R.id.text)
        title.text = fileList[position].name
        item.setOnClickListener {
            val bundle = bundleOf("data" to fileList[position].id)
            item.findNavController().navigate(R.id.editorFragment, bundle)
        }
    }
}
