package com.mydigitalmedicaljournal.ui.templates.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class CategoryRecyclerAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_category, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return CategoryViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    Log.d("TEXT", "backup")
                }
            }
        }
        holder.textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size

}