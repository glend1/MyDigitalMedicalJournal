package com.mydigitalmedicaljournal.ui.templates.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

import com.mydigitalmedicaljournal.R

class CategoryRecyclerAdapter(private val myDataset: Array<String>) : RecyclerView.Adapter<CategoryRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        return CategoryRecyclerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewHolder, position: Int) {
        val pagerAdapter = CategoryPagerAdapter()
        val pager: ViewPager2 = holder.itemView.findViewById(R.id.category_pager)
        pager.adapter = pagerAdapter
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    Log.d("TEXT", "backup")
                }
            }
        }
        //holder.pagerAdapter.setText(myDataset[position])
    }

    override fun getItemCount() = myDataset.size

}

class CategoryRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v)

