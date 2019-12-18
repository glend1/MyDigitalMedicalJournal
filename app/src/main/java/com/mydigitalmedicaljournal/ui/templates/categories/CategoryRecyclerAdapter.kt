package com.mydigitalmedicaljournal.ui.templates.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class CategoryRecyclerAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_category, parent, false)

        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
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