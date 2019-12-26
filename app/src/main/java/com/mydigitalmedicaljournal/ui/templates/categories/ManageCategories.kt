package com.mydigitalmedicaljournal.ui.templates.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mydigitalmedicaljournal.R


class ManageCategories : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO make json for categories
        val root = inflater.inflate(R.layout.fragment_manage_categories, container, false)
        val array = arrayOf("this", "is", "a", "test")
        val viewAdapter = CategoryRecyclerAdapter(array)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_category)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter

        return root

    }
}

