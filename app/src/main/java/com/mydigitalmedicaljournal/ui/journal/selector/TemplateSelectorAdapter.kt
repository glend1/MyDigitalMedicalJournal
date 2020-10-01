package com.mydigitalmedicaljournal.ui.journal.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateSelectorAdapter(private var fileList: MutableList<TemplateList.NestedTemplates>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount() = fileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.itemView
        val title = item.findViewById<TextView>(R.id.text)
        title.text = fileList[position].category
        //TODO this needs finishing
        /*item.setOnClickListener {
            val bundle = bundleOf("data" to fileList[position].id)
            item.findNavController().navigate(R.id.editorFragment, bundle)
        }*/
    }
}