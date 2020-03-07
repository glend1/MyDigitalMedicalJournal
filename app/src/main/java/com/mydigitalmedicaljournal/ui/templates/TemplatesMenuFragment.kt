package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.NamedResource
import com.mydigitalmedicaljournal.ui._generics.CustomDivider


class TemplatesMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        //TODO some fragments are missing here
        val menu = mutableListOf(
            NamedResource(R.string.ManageCategories, R.id.manageCategories),
            NamedResource(R.string.ManageTemplates, R.id.manageTemplates),
            NamedResource(R.string.ManageFlares, null),
            NamedResource(R.string.Import, null),
            NamedResource(R.string.Export, null),
            NamedResource(R.string.Backup, null),
            NamedResource(R.string.Download, null),
            NamedResource(R.string.Upload, null)
        )
        val viewAdapter = TemplateMenuRecyclerAdapter(menu, findNavController())
        val templateList = root.findViewById<RecyclerView>(R.id.template_list)
        val itemDecoration = CustomDivider(context!!)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        return root
    }
}