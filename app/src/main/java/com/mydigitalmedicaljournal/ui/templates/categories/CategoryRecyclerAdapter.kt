package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

import com.mydigitalmedicaljournal.R

class CategoryRecyclerAdapter(private val myDataset: Array<String>) : RecyclerView.Adapter<CategoryRecyclerViewHolder>() {

    lateinit var textView: TextView
    lateinit var pagerAdapter: CategoryPagerAdapter
    lateinit var pager: ViewPager2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        val view = CategoryRecyclerViewHolder(layout)
        pagerAdapter = CategoryPagerAdapter(CategoryModel.values())
        pager = view.itemView.findViewById(R.id.category_pager)
        pager.adapter = pagerAdapter
        return view
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewHolder, position: Int) {
        pagerAdapter.text = myDataset[position]
        pagerAdapter.pager = pager
    }

    override fun getItemCount() = myDataset.size

}

class CategoryRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v)

