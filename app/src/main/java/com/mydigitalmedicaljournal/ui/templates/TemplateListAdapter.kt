package com.mydigitalmedicaljournal.ui.templates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class TemplateListAdapter(private val myDataset: Array<String>, private val fragment: Fragment) :
    RecyclerView.Adapter<TemplateViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplateViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return TemplateViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
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
        holder.textView.text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

}