package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.mydigitalmedicaljournal.R

class CategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textView = v.findViewById<TextView>(R.id.text_item)

    init {
        val vp = v.findViewById<ViewPager>(R.id.cagegory_pager)
        val pagerAdapter =
            CategoryPagerAdapter()
        vp.adapter = pagerAdapter
    }

}