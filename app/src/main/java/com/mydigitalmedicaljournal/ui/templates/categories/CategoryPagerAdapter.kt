package com.mydigitalmedicaljournal.ui.templates.categories

import android.view.View
import androidx.viewpager.widget.PagerAdapter

class CategoryPagerAdapter: PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount() = 2

}