package com.mydigitalmedicaljournal.ui.templates.templates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateListAdapter(var json: TemplateManager, val layout: Int, private val navController: NavController) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = 1 //json.data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.text)
        //title.text = json.data[position].name
        holder.itemView.setOnClickListener {
            //val bundle = bundleOf("data" to json.data[position].id)
            //navController.navigate(R.id.editorFragment, bundle)
        }
    }
}
