package com.mydigitalmedicaljournal.ui.templates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.NamedResource
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateMenuRecyclerAdapter(private val namedResourceList: MutableList<NamedResource>, private val navController: NavController) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.text)
        textView.text = namedResourceList[position].getName(textView.context)
        if (namedResourceList[position].resource != null){
            holder.itemView.setOnClickListener {
                navController.navigate(namedResourceList[position].resource!!)
            }
        }
    }

    override fun getItemCount() = namedResourceList.size

}