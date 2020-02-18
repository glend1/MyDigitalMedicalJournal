package com.mydigitalmedicaljournal.ui.templates

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class TemplateRecyclerAdapter(private val myDataSet: Array<String>, private val fragment: Fragment) : RecyclerView.Adapter<TemplateRecyclerAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TemplateViewHolder(layout)
    }


    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val navController = findNavController(fragment)
            when (position) {
                0 -> {
                    Log.d("TEXT", "backup")
                }
                1 -> {
                    Log.d("TEXT", "import")
                }
                2 -> {
                    navController.navigate(R.id.manageTemplates)
                }
                3 -> {
                    Log.d("TEXT", "export")
                }
                4 -> {
                    navController.navigate(R.id.manageCategories)
                }
                5 -> {
                    Log.d("TEXT", "bad days")
                }
                6 -> {
                    Log.d("TEXT", "download")
                }
                7 -> {
                    Log.d("TEXT", "upload")
                }
            }
        }
        holder.textView.text = myDataSet[position]
    }

    override fun getItemCount() = myDataSet.size

    inner class TemplateViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.text_item)
    }

}