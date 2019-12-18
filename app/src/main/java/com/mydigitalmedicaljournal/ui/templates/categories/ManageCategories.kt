package com.mydigitalmedicaljournal.ui.templates.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.mydigitalmedicaljournal.R


class ManageCategories : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO make json for categories



        // Provide a reference to the views for each data item

        val root = inflater.inflate(R.layout.fragment_manage_categories, container, false)
        val array = arrayOf("this", "is", "a", "test")
        val viewAdapter = CategoryListAdapter(array)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_category)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter

        return root

    }
}

