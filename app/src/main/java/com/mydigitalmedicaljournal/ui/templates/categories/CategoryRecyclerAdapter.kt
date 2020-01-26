package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories

class CategoryRecyclerAdapter(val cat: Categories) : RecyclerView.Adapter<CategoryRecyclerViewHolder>() {

    lateinit var textView: TextView
    private lateinit var pagerAdapter: CategoryPagerAdapter
    lateinit var pager: ViewPager2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecyclerViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_pager, parent, false)
        //registerAdapterDataObserver(cat)
        return CategoryRecyclerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewHolder, position: Int) {
        pagerAdapter = CategoryPagerAdapter(CategoryModel.values(), this)
        pager = holder.itemView.findViewById(R.id.pager)
        pager.adapter = pagerAdapter
        pagerAdapter.text = cat.data[position].name
        pagerAdapter.pager = pager
        pagerAdapter.position = position
    }

    override fun getItemCount() = cat.data.size

    //TODO add sort feature?
}

class CategoryRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v)

