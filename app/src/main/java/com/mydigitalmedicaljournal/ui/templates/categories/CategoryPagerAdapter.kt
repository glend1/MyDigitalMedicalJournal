package com.mydigitalmedicaljournal.ui.templates.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoryPagerAdapter: RecyclerView.Adapter<CategoryPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPagerViewHolder {
        val model: CategoryModel = CategoryModel.values().get(viewType)
        val layout = LayoutInflater.from(parent.context).inflate(model.layoutResId, parent, false)
        //TODO values aren't returning correctly
        return CategoryPagerViewHolder(layout)
    }

    override fun getItemCount() = 2
    //TODO replace with model

    override fun onBindViewHolder(holder: CategoryPagerViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    Log.d("TEXT", "backup")
                }
            }
        }
    }
}

class CategoryPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)