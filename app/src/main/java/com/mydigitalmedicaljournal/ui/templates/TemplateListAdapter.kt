package com.mydigitalmedicaljournal.ui.templates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateListAdapter(private var fileList: List<TemplateList.FileList>, val layout: Int, private val navController: NavController) : RecyclerView.Adapter<ViewHolder>() {
    //TODO this needs testing
    override fun getItemCount() = fileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.text)
        title.text = fileList[position].name
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("data" to fileList[position].id)
            navController.navigate(R.id.editorFragment, bundle)
        }
    }
}
