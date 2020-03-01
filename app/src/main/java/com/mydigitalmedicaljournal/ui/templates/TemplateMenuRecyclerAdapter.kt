package com.mydigitalmedicaljournal.ui.templates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateMenuRecyclerAdapter(private val myDataSet: Array<String>, private val fragment: Fragment) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val navController = findNavController(fragment)
            when (position) {
                //TODO this is kind of fragile, changing the list order breaks navigation
                0 -> {
                    navController.navigate(R.id.manageCategories)
                }
                1 -> {
                    navController.navigate(R.id.manageTemplates)
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
                5 -> {
                }
                6 -> {
                }
                7 -> {
                }
            }
        }
        holder.itemView.findViewById<TextView>(R.id.text_item).text = myDataSet[position]
    }

    override fun getItemCount() = myDataSet.size
}