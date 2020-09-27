package com.mydigitalmedicaljournal.ui.journal.selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui.templates.TemplateListAdapter

class TemplateSelector : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_selector, container, false)
        val template = TemplateList.getTemplates(requireContext())
        val viewAdapter =
            TemplateListAdapter(
                template,
                R.layout.list_item,
                findNavController()
            )
        val templateList = root.findViewById<RecyclerView>(R.id.selector_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        return root
    }
}
