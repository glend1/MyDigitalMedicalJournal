package com.mydigitalmedicaljournal.ui.templates

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.NamedResource
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateMenuRecyclerAdapter(private val namedResourceList: MutableList<NamedResource>, private val navController: NavController) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text).text = namedResourceList[position].getName(context)
        if (namedResourceList[position].resource != null){
            holder.itemView.setOnClickListener {
                navController.navigate(namedResourceList[position].resource!!)
            }
        }
    }

    override fun getItemCount() = namedResourceList.size

}