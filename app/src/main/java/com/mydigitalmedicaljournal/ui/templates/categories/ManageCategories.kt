package com.mydigitalmedicaljournal.ui.templates.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories


class ManageCategories : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO make json for categories
        val root = inflater.inflate(R.layout.fragment_manage_categories, container, false)
        //val cat = Categories(context!!)
        val cat = Categories(context!!)
        val viewAdapter = CategoryRecyclerAdapter(cat)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_category)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            //TODO set action
            Log.i("ADD", "button clicked")
        }
        return root

    }
}

