package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R


class TemplatesFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_templates, container, false)

        val viewAdapter = TemplateRecyclerAdapter(resources.getStringArray(R.array.template_list), this)
        val templateList = root.findViewById<RecyclerView>(R.id.template_list)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter

        return root

    }
}