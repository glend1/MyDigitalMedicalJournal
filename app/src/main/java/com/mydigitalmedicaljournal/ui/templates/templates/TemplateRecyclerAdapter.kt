package com.mydigitalmedicaljournal.ui.templates.templates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories

class TemplateRecyclerAdapter(val cat: Categories) : RecyclerView.Adapter<TemplateRecyclerViewHolder>() {

    lateinit var textView: TextView
    private lateinit var pagerAdapter: TemplatePagerAdapter
    lateinit var pager: ViewPager2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateRecyclerViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        //registerAdapterDataObserver(cat)
        return TemplateRecyclerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TemplateRecyclerViewHolder, position: Int) {
        pagerAdapter = TemplatePagerAdapter(TemplateModel.values(), this)
        pager = holder.itemView.findViewById(R.id.category_pager)
        pager.adapter = pagerAdapter
        pagerAdapter.text = cat.data[position].name
        pagerAdapter.pager = pager
        pagerAdapter.position = position
    }

    override fun getItemCount() = cat.data.size

}

class TemplateRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v)
